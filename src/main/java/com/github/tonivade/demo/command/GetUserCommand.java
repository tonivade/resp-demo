package com.github.tonivade.demo.command;

import static com.github.tonivade.resp.protocol.RedisToken.string;

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

    User user = userRepository.findOne(userId.toString());

    if (user != null)
    {
      return RedisToken.array(string("id"), string(user.getId()), string("name"), string(user.getName()));
    }
    else
    {
      return RedisToken.error("user not found: " + userId);
    }
  }
}
