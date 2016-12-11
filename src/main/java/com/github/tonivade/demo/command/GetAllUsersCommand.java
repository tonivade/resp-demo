package com.github.tonivade.demo.command;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tonivade.demo.repo.User;
import com.github.tonivade.demo.repo.UserRepository;
import com.github.tonivade.resp.annotation.Command;
import com.github.tonivade.resp.command.ICommand;
import com.github.tonivade.resp.command.IRequest;
import com.github.tonivade.resp.command.IResponse;

@Command("getallusers")
public class GetAllUsersCommand implements ICommand {
 
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public void execute(IRequest request, IResponse response) {
    List<String> userIds = new LinkedList<>();
    for (User user : userRepository.findAll()) {
      userIds.add(user.getId());
    }
    response.addArray(userIds);
  }

}
