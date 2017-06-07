package com.github.tonivade.demo.command;

import static com.github.tonivade.resp.protocol.RedisToken.responseOk;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tonivade.demo.repo.User;
import com.github.tonivade.demo.repo.UserRepository;
import com.github.tonivade.resp.annotation.Command;
import com.github.tonivade.resp.annotation.ParamLength;
import com.github.tonivade.resp.command.Request;
import com.github.tonivade.resp.command.RespCommand;
import com.github.tonivade.resp.protocol.RedisToken;
import com.github.tonivade.resp.protocol.SafeString;

@Command("createuser")
@ParamLength(2)
public class CreateUserCommand implements RespCommand
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public RedisToken execute(Request request)
  {
    userRepository.save(createUserFrom(request));

    return responseOk();
  }

  private User createUserFrom(Request request)
  {
    SafeString id = request.getParam(0);
    SafeString name = request.getParam(1);
    return new User(id.toString(), name.toString());
  }
}
