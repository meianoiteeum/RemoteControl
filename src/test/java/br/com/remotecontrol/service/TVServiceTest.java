package br.com.remotecontrol.service;

import br.com.remotecontrol.exceptions.DeviceOperationException;
import br.com.remotecontrol.model.TV;
import br.com.remotecontrol.service.impl.TVServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TVServiceTest {

    private TV tv;
    private TVService service;

    @BeforeEach
    void setUp(){
        tv = new TV("Samsung","4k");
        service = new TVServiceImpl();
    }

    @Test
    @DisplayName("Verifica se a TV vai ligar corretamente")
    void checkIfTurnON(){
        tv.setPowered(false);

        service.turnOn(tv);

        assertThat(tv.isPowered()).isTrue();
    }

    @Test
    @DisplayName("Verifica se acontece IllegalArgumentException para parâmetro NULL")
    void checkIfDeviceIsNotValidate(){
        var exception = assertThrows(IllegalArgumentException.class, ()-> service.turnOn(null));

        assertThat(exception.getMessage()).isEqualTo("TV cannot be null");
    }

    @Test
    @DisplayName("Verifica se a TV vai desligar corretamente")
    void checkIfTurnOFF(){
        tv.setPowered(true);

        service.turnOff(tv);

        assertThat(tv.isPowered()).isFalse();
    }

    @Test
    @DisplayName("Verifica se vai mudar de canal")
    void checkIfChangeChannel(){
        tv.setPowered(true);

        service.changeChannel(tv, 5);

        assertThat(tv.getChannel()).isEqualTo(5);
    }

    @Test
    @DisplayName("Verifica se falha a mudança de canal por estar desligada a TV")
    void checkIfExceptionWithTVOFF(){
        tv.setPowered(false);

        var exception = assertThrows(DeviceOperationException.class, () -> service.changeChannel(tv, 5));
        var cause = exception.getCause();

        assertThat(exception.getMessage()).isEqualTo("Failed to change channel");
        validateExceptionTVOFF(cause);
    }

    @Test
    @DisplayName("Verifica se vai mudar o volume")
    void checkIfAdjustVolume(){
        tv.setPowered(true);

        service.adjustVolume(tv, 5);

        assertThat(tv.getVolume()).isEqualTo(5);
    }

    @Test
    @DisplayName("Verifica os limites MAX e MIN")
    void checkMinMaxThrows(){
        tv.setPowered(true);

        var minException = assertThrows(IllegalArgumentException.class, () -> service.adjustVolume(tv, -1));
        var maxException = assertThrows(IllegalArgumentException.class, () -> service.adjustVolume(tv, 101));

        assertThat(minException.getMessage()).isEqualTo("Please, enter a number between 0 and 100");
        assertThat(maxException.getMessage()).isEqualTo("Please, enter a number between 0 and 100");
    }

    @Test
    @DisplayName("Verifica se vai mudar o volume")
    void checkThrowsTVOFF(){
        tv.setPowered(false);

        var exception = assertThrows(DeviceOperationException.class, () -> service.adjustVolume(tv, -1));
        var cause = exception.getCause();

        assertThat(exception.getMessage()).isEqualTo("Failed to adjust volume");
        validateExceptionTVOFF(cause);
    }

    private static void validateExceptionTVOFF(Throwable cause) {
        assertThat(IllegalStateException.class).isEqualTo(cause.getClass());
        assertThat(cause.getMessage()).isEqualTo("TV is off");
    }

}