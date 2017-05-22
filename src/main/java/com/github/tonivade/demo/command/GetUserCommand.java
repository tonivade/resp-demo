package com.github.tonivade.demo.command;

import static com.github.tonivade.resp.protocol.RedisToken.array;
import static com.github.tonivade.resp.protocol.RedisToken.error;
import static com.github.tonivade.resp.protocol.RedisToken.string;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tonivade.demo.repo.User;
import com.github.tonivade.demo.repo.UserRepository;
import com.github.tonivade.resp.annotation.Command;
import com.github.tonivade.resp.annotation.ParamLength;
import com.github.tonivade.resp.command.ICommand;
import com.github.tonivade.resp.command.IRequest;
import com.github.tonivade.resp.protocol.RedisToken;
import com.github.tonivade.resp.protocol.SafeString;

@Command("getuser")
@ParamLength(1)
public class GetUserCommand implements ICommand
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public RedisToken<?> execute(IRequest request)
  {
    SafeString userId = request.getParam(0);

    Optional<User> userOptional = userRepository.findById(userId.toString());

    return userOptional.map(this::toArray).orElse(notFound(userId));
  }

  private RedisToken notFound(SafeString userId)
  {
    return error("user not found: " + userId);
  }

  private RedisToken toArray(User user)
  {
    return array(string("id"), string(user.getId()), string("name"), string(user.getName()));
  }
}
