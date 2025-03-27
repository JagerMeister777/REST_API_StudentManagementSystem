package raisetech.rest.api.studentManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@OpenAPIDefinition(
    info = @Info(
        title = "受講生管理システム",
        description = "受講生情報を管理するシステムです。受講生の個人情報と受講しているコース情報を管理、CRUD操作を行うことができます。"
    ),
		servers = {
				@Server(url = "http://localhost:8080",description = "ローカル環境")
		}
)
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
