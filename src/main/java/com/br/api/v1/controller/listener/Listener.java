package com.br.api.v1.controller.listener;
import com.br.api.v1.model.DepartamentoModel;
import com.br.api.v1.model.OrgaoModel;
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

    @RabbitListener(queues = "government-document")
    public void onOrgaoCreated01(OrgaoModel orgaoModel) {
        System.out.println("Orgão recebido: " + orgaoModel.getOrgaoId());
        System.out.println("Nome: " + orgaoModel.getNome());
        System.out.println("Ativo: " + orgaoModel.isActive());
        System.out.println("CEP: " + orgaoModel.getEndereco().getCep());
        System.out.println("Cidade ID: " + orgaoModel.getEndereco().getCidade().getId());
        System.out.println("Estado ID: " + orgaoModel.getEndereco().getCidade().getEstado().getId());
    }

}
