package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DynamicControlsPage {
    private final Page page;

    // Локаторы
    private final String removeButton = "button:has-text('Remove')";
    private final String addButton = "button:has-text('Add')";
    private final String checkbox = "#checkbox";
    private final String loadingIndicator = "#loading";
    private final String message = "#message";

    public DynamicControlsPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
    }

    public void clickRemoveButton() {
        page.click(removeButton);
        waitForLoadingToComplete();
    }

    public void clickAddButton() {
        page.click(addButton);
        waitForLoadingToComplete();
    }

    public boolean isCheckboxVisible() {
        return page.locator(checkbox).isVisible();
    }

    public String getMessage() {
        return page.locator(message).textContent();
    }

    public void waitForLoadingToComplete() {
        // Ждем появления индикатора загрузки
        page.waitForSelector(loadingIndicator, 
            new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        
        // Ждем исчезновения индикатора загрузки
        page.waitForSelector(loadingIndicator,
            new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }

    // Дополнительные методы для гибкости тестов
    public boolean isRemoveButtonVisible() {
        return page.locator(removeButton).isVisible();
    }

    public boolean isAddButtonVisible() {
        return page.locator(addButton).isVisible();
    }
}
