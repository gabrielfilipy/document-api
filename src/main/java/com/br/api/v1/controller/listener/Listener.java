package com.br.api.v1.controller.listener;
import com.br.api.v1.model.DepartamentoModel;
import com.br.api.v1.model.UserModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @RabbitListener(queues = "user-document")
        public void userModel(UserModel userModel) {
            System.out.println("usuário cadastrados: " + userModel.getNome());
    }
    @RabbitListener(queues = "department-document")
    public void departmentModel(DepartamentoModel departamentoModel) {
        System.out.println("departamento cadastrado: " + departamentoModel.getNome());
    }
}
