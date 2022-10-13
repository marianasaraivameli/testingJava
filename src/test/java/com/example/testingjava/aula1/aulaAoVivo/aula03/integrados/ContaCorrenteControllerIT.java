package com.example.testingjava.aula1.aulaAoVivo.aula03.integrados;

import com.example.testingjava.aula1.aulaAoVivo.aula03.dao.ContaDAO;
import com.example.testingjava.aula1.aulaAoVivo.aula03.model.ContaCorrente;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class ContaCorrenteControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContaDAO contaDAO;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

//    @BeforeEach
//    void setup() {
//        // fazer o metodo deleteAll no ContaDAO
//        contaDAO.deleteAll();
//        log.info("Contas: " + contaDAO.listarTodasContas().size());
//    }

    @Test
    void novaContaCorrente_returnContaCorrente_quandoCriarComSucesso() throws Exception {
        String nomeCliente = "Cliente 1";
//        ContaCorrente cc = new ContaCorrente(1, "Cliente 1");

        ResultActions resposta = mockMvc.perform(
                post("/cc/{cliente}", nomeCliente)
                        .contentType(MediaType.APPLICATION_JSON) );

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.cliente", CoreMatchers.is(nomeCliente)))
                .andExpect(jsonPath("$.saldo", CoreMatchers.is(0.0)));

        assertThat(contaDAO.listarTodasContas().size()).isEqualTo(1);
        assertThat(contaDAO.listarTodasContas().get(0)).isNotNull();
        log.info(contaDAO.listarTodasContas().get(0));
        ContaCorrente e = objectMapper.readValue(resposta.andReturn().getResponse().getContentAsString(), ContaCorrente.class);
        log.info(e);
    }

    @Test
    void getContaCorrente_returnoContaCorrente_quandoContaExiste() throws Exception {
        ContaCorrente contaCorrente = contaDAO.novaContaCorrente("Cliente ");

        ResultActions resposta = mockMvc.perform(
                get("/cc/{numero}", contaCorrente.getNumero())
                        .contentType(MediaType.APPLICATION_JSON) );

        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente", CoreMatchers.is(contaCorrente.getCliente())))
                .andExpect(jsonPath("$.numero", CoreMatchers.is(contaCorrente.getNumero())));


    }
}
