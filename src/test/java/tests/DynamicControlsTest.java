package tests;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pages.DynamicControlsPage;
import utils.TestContext;

public class DynamicControlsTest {
    private TestContext context;
    private DynamicControlsPage controlsPage;

    @BeforeEach
    public void setup() {
        // Ручное внедрение зависимости через конструктор
        context = new TestContext();
        controlsPage = new DynamicControlsPage(context.getPage());
        controlsPage.navigate();
    }

    @Test
    @DisplayName("Чекбокс исчезает после нажатия кнопки Remove")
    public void testCheckboxRemoval() {
        // Проверяем, что чекбокс видим изначально
        assertTrue(controlsPage.isCheckboxVisible(), 
            "Чекбокс должен быть видим перед удалением");

        // Нажимаем кнопку Remove
        controlsPage.clickRemoveButton();

        // Проверяем, что чекбокс исчез
        assertFalse(controlsPage.isCheckboxVisible(), 
            "Чекбокс должен исчезнуть после нажатия Remove");

        // Проверяем сообщение об успешном удалении
        assertEquals("It's gone!", controlsPage.getMessage());
    }

    @Test
    @DisplayName("Чекбокс появляется после нажатия кнопки Add")
    public void testCheckboxReappearance() {
        // Сначала удаляем чекбокс
        controlsPage.clickRemoveButton();
        assertFalse(controlsPage.isCheckboxVisible());

        // Затем добавляем обратно
        controlsPage.clickAddButton();

        // Проверяем, что чекбокс снова видим
        assertTrue(controlsPage.isCheckboxVisible(), 
            "Чекбокс должен появиться после нажатия Add");

        // Проверяем сообщение
        assertEquals("It's back!", controlsPage.getMessage());
    }

    @Test
    @DisplayName("Кнопки меняют состояние корректно")
    public void testButtonStateChanges() {
        // Изначально видна кнопка Remove
        assertTrue(controlsPage.isRemoveButtonVisible());
        assertFalse(controlsPage.isAddButtonVisible());

        // Нажимаем Remove
        controlsPage.clickRemoveButton();

        // Теперь должна быть видна кнопка Add
        assertFalse(controlsPage.isRemoveButtonVisible());
        assertTrue(controlsPage.isAddButtonVisible());
    }

    @AfterEach
    public void teardown() {
        if (context != null) {
            context.close();
        }
    }
}
