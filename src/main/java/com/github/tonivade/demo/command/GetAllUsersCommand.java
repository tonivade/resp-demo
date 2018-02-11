package com.github.tonivade.demo.command;

import static com.github.tonivade.resp.protocol.RedisToken.array;
import static com.github.tonivade.resp.protocol.RedisToken.string;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tonivade.demo.repo.User;
import com.github.tonivade.demo.repo.UserRepository;
import com.github.tonivade.resp.annotation.Command;
import com.github.tonivade.resp.command.Request;
import com.github.tonivade.resp.command.RespCommand;
import com.github.tonivade.resp.protocol.RedisToken;

@Command("get all users")
public class GetAllUsersCommand implements RespCommand {
  @Autowired
  private UserRepository userRepository;

  @Override
  public RedisToken execute(Request request) {
    List<RedisToken> userIds = new LinkedList<>();
    for (User user : userRepository.findAll()) {
      userIds.add(string(user.getId()));
    }
    return array(userIds);
  }
}
