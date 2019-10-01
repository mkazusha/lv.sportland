package webPages;

import baseFunc.BaseFunc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class ProductsPage {
    private BaseFunc baseFunc;
    private final By GENDER_SECTIONS = By.xpath(".//ul[@id='menu-product-menu1']/li");

    public ProductsPage(BaseFunc basefunc) {
        this.baseFunc = basefunc;
    }

    public void selectProductSection(String section, String category, String gender) {
        List<WebElement> genderSections = baseFunc.getAllElements(GENDER_SECTIONS);
        for (int i = 0; i < genderSections.size(); i++) {
            if (genderSections.get(i).getText().contains(gender)) {
                genderSections.get(i).click();
                List<WebElement> categories = genderSections.get(i).findElements(By.tagName("a"));
                for (int j = 0; j < categories.size(); j++) {
                    if (categories.get(j).getText().equals(category)) {
                        for (int k = j + 1; k < categories.size(); k++) {
                            if (categories.get(k).getText().equals(section)) {
                                categories.get(k).click();
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
}
