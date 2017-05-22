package com.github.tonivade.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.tonivade.demo.command.CreateUserCommand;
import com.github.tonivade.demo.command.GetAllUsersCommand;
import com.github.tonivade.demo.command.GetUserCommand;
import com.github.tonivade.resp.command.CommandSuite;
import com.github.tonivade.resp.command.CommandWrapperFactory;

@SpringBootApplication
public class DemoApplication
{
  @Bean
  public CommandSuite commandSuite(CommandWrapperFactory factory)
  {
    return new CommandSuite(factory) {{
      addCommand(GetUserCommand.class);
      addCommand(CreateUserCommand.class);
      addCommand(GetAllUsersCommand.class);
    }};
  }

  public static void main(String[] args)
  {
    SpringApplication.run(DemoApplication.class, args);
  }
}
