package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Helper {
    private BaseFunc baseFunc;
    private final By HAMBURGER_BTN = By.xpath(".//button[@class='menu-trigger js-toggle-menu']");
    private final By MENU_SECTIONS = By.xpath(".//ul[@id='menu-primary-menu']/li");
    private final By PRODUCT_TITLE = By.xpath(".//p[@class='spodb-product-card__title']");
    private final By PRODUCT_SALE = By.xpath(".//p[@class = 'spodb-product-card__percentage']");

    public Helper(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void pushHamburger_BTN() {
        baseFunc.getElement(HAMBURGER_BTN).click();
    }

    public void selectMenuSection(String sectionName) {
        List<WebElement> menuSections = baseFunc.getAllElements(MENU_SECTIONS);
        for (WebElement section : menuSections) {
            if (section.getText().contains(sectionName)) {
                section.click();
                break;
            }
        }
    }

    public void checkBrand(String brandName) {
        brandName = brandName.toUpperCase();
        baseFunc.waitJsExecution(PRODUCT_TITLE);
        List<WebElement> brands = baseFunc.getAllElements(PRODUCT_TITLE);
        for (WebElement brand : brands) {
            Assertions.assertTrue(brand.getText().contains(brandName), "Filter is not working!!! Wrong brand!!!!");
        }
    }

    public void checkSale() {
        List<WebElement> sales = baseFunc.getAllElements(PRODUCT_SALE);
        for (WebElement sale : sales) {
            Assertions.assertTrue(sale.getText().contains("%"));
        }
    }
}
