package com.github.tonivade.demo.command;

import static com.github.tonivade.resp.protocol.RedisToken.array;
import static com.github.tonivade.resp.protocol.RedisToken.string;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tonivade.demo.repo.User;
import com.github.tonivade.demo.repo.UserRepository;
import com.github.tonivade.resp.annotation.Command;
import com.github.tonivade.resp.command.ICommand;
import com.github.tonivade.resp.command.IRequest;
import com.github.tonivade.resp.protocol.RedisToken;

@Command("getallusers")
public class GetAllUsersCommand implements ICommand
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public RedisToken<?> execute(IRequest request)
  {
    List<RedisToken<?>> userIds = new LinkedList<>();
    for (User user : userRepository.findAll())
    {
      userIds.add(string(user.getId()));
    }
    return array(userIds);
  }
}
