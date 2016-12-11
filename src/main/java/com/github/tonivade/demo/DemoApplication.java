package com.github.tonivade.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.tonivade.demo.command.CreateUserCommand;
import com.github.tonivade.demo.command.GetAllUsersCommand;
import com.github.tonivade.demo.command.GetUserCommand;
import com.github.tonivade.resp.RedisServer;
import com.github.tonivade.resp.command.CommandSuite;
import com.github.tonivade.resp.command.CommandWrapperFactory;

@SpringBootApplication
public class DemoApplication
{
  @Value("${demo.host}")
  private String host;
  @Value("${demo.port}")
  private int port;

  @Bean(initMethod= "start")
  @Autowired
  public RedisServer server(CommandSuite commands)
  {
    return new RedisServer(host, port, commands);
  }

  @Bean
  @Autowired
  public CommandSuite commandSuite(CommandWrapperFactory factory)
  {
    return new CommandSuite(factory) {{
      addCommand(GetUserCommand.class);
      addCommand(CreateUserCommand.class);
      addCommand(GetAllUsersCommand.class);
    }};
  }

  @Bean
  public CommandWrapperFactory commandWrapperFactory(AutowireCapableBeanFactory factory)
  {
    return new SpringCommandWrapperFactory(factory);
  }

  public static void main(String[] args)
  {
    SpringApplication.run(DemoApplication.class, args);
  }
}
