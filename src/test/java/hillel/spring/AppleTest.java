package hillel.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class AppleTest {

    @Test
    public void lombokWorks() throws Exception {
        val apple = new Apple(100, "Green");

        assertThat(apple.getColor()).isEqualTo("Green");

        assertThat(apple).isEqualTo(new Apple(100, "Green"));
    }
}