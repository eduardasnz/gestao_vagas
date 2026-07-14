package com.eduardasnz.gestao_vagas.modules.company.controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eduardasnz.gestao_vagas.modules.company.dtos.CreateJobDTO;
import com.eduardasnz.gestao_vagas.modules.company.entities.CompanyEntity;
import com.eduardasnz.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.eduardasnz.gestao_vagas.utils.TestUtils;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
    }

    @Test
    public void deve_ser_possivel_criar_uma_nova_vaga() throws Exception {

        var company = CompanyEntity.builder()
        .name("CompanyTest").username("company-test")
        .email("contato@companytest.com")
        .password("password-test-ai")
        .website("companytest2026.com.br").build();

        company = companyRepository.saveAndFlush(company);
        var createJobDTO = CreateJobDTO.builder().benefits("BENEFITS_TEST").description("DESCRIPTION TEST")
                .level("LEVEL_TEST").build();

        var result = mvc.perform(
                MockMvcRequestBuilders.post("/company/jobs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(createJobDTO))
                    .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_MBAPPE")))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        System.out.println(result);
    }

}
