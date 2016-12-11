package com.github.tonivade.demo.command;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tonivade.demo.repo.User;
import com.github.tonivade.demo.repo.UserRepository;
import com.github.tonivade.resp.annotation.Command;
import com.github.tonivade.resp.annotation.ParamLength;
import com.github.tonivade.resp.command.ICommand;
import com.github.tonivade.resp.command.IRequest;
import com.github.tonivade.resp.command.IResponse;
import com.github.tonivade.resp.protocol.SafeString;

@Command("getuser")
@ParamLength(1)
public class GetUserCommand implements ICommand
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public void execute(IRequest request, IResponse response)
  {
    SafeString userId = request.getParam(0);

    User user = userRepository.findOne(userId.toString());

    if (user != null)
    {
      response.addArray(asList("id", user.getId(), "name", user.getName()));
    }
    else
    {
      response.addError("user not found: " + userId);
    }
  }
}
