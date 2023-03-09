package controller;

import api.AccountServicePort;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptionRessource.ApiError;
import models.Account;
import models.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

        @MockBean
        private AccountServicePort accountServicePort;
        @MockBean
        private Clock clock;
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        ObjectMapper objectMapper;

        @Test
        void findById_WHEN_id_exists_THEN_return_account() throws Exception {
            // Given
            Account account =
                    Account.builder()
                            .id(1L)
                            .type(Type.Courant)
                            .build();

            when(accountServicePort.getById(account.id())).thenReturn(Optional.of(account));

            // When
            MvcResult mvcResult =
            mockMvc.perform(get("/account/{rib}",account.id())
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                            .andExpect(status().isOk())
                            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                            .andReturn();

            // Then
            var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Account.class);

            assertThat(result.id()).isEqualTo(account.id());
            assertThat(result.type()).isEqualTo(account.type());

        }
    @Test
    void findById_WHEN_error_occurred_THEN_return_ApiError_404() throws Exception {
        // Given
        long accountRib = 1L;
        Instant instant = Instant.parse("2023-02-21T15:12:00.00Z");
        ZoneId zoneId = ZoneId.of("Europe/Paris");

        when(accountServicePort.getById(accountRib)).thenReturn(Optional.empty());
        when(clock.instant()).thenReturn(instant);
        when(clock.getZone()).thenReturn(zoneId);

        // When
        MvcResult mvcResult =
                mockMvc
                        .perform(get("/account/{rib}", accountRib).contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isNotFound())
                        .andReturn();

        // Then
        var result =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ApiError.class);

    /*    assertThat(result.status()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(result.label()).isEqualTo(HttpStatus.NOT_FOUND.getReasonPhrase());
        assertThat(result.message()).isEqualTo(format("Account not found"+ accountRib));
        assertThat(result.path()).isEqualTo(format("/account%s", accountRib));
        assertThat(result.timestamp()).isEqualTo(OffsetDateTime.now(clock));*/
    }

    @Test
    void findAll_THEN_return_all_accounts() throws Exception {
        // Given
        Account account1 =
                Account.builder()
                        .type(Type.Courant)
                        .id(1L)
                        .build();

        Account account2 =
                Account.builder()
                        .id(2L)
                        .type(Type.Epargne)
                        .build();

        var expected = List.of(account1, account2);

        when(accountServicePort.getAll()).thenReturn(List.of(account1, account2));

        // When
        MvcResult mvcResult = mockMvc.perform(get("/account/all")).andExpect(status().isOk()).andReturn();

        // Then
        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), new TypeReference<List<Account>>() {});

        assertThat(result).isEqualTo(expected);
    }
    @Test
    void findAll_WHEN_unexpected_error_occurred_THEN_return_ApiError_500() throws Exception {
        // Given
        Instant instant = Instant.parse("2023-02-21T15:12:00.00Z");
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");

        when(accountServicePort.getAll()).thenThrow(new RuntimeException("Unexpected error."));
        when(clock.instant()).thenReturn(instant);
        when(clock.getZone()).thenReturn(zoneId);

        // When
        MvcResult mvcResult =
                mockMvc
                        .perform(get("/accounts/all").contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isInternalServerError())
                        .andReturn();

        // Then
        var result =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ApiError.class);

        assertThat(result.status()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(result.label()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        assertThat(result.message()).isEqualTo("Unexpected error.");
        assertThat(result.path()).isEqualTo("/accounts/all");
        assertThat(result.timestamp()).isEqualTo(OffsetDateTime.now(clock));
    }

}
