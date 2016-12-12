package com.github.tonivade.demo.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tonivade.demo.repo.User;
import com.github.tonivade.demo.repo.UserRepository;
import com.github.tonivade.resp.annotation.Command;
import com.github.tonivade.resp.annotation.ParamLength;
import com.github.tonivade.resp.command.ICommand;
import com.github.tonivade.resp.command.IRequest;
import com.github.tonivade.resp.command.IResponse;
import com.github.tonivade.resp.protocol.SafeString;

@Command("createuser")
@ParamLength(2)
public class CreateUserCommand implements ICommand
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public void execute(IRequest request, IResponse response)
  {
    userRepository.save(createUserFrom(request));

    response.addSimpleStr(IResponse.RESULT_OK);
  }

  private User createUserFrom(IRequest request)
  {
    SafeString id = request.getParam(0);
    SafeString name = request.getParam(1);
    return new User(id.toString(), name.toString());
  }
}
