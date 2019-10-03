package webPages;

import baseFunc.BaseFunc;


import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.io.*;
import java.util.List;


public class MenFootballShoesPage {
    private final By SORTING_OPTIONS = By.xpath(".//select");
    private final By FILTER_NAMES = By.xpath(".//fieldset/ol/li/label");
    private final By FILTER_CHECKBOX = By.xpath(".//input[@type='checkbox']");
    private final By PRODUCT_INFORMATION = By.xpath(".//a[@class = 'spodb-product-card']");

    private BaseFunc baseFunc;

    public MenFootballShoesPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectSortingMethod(String sortingMethod) {
        Select dropdown = new Select(baseFunc.getElement(SORTING_OPTIONS));
        dropdown.selectByVisibleText(sortingMethod);
    }

    public void selectFilters(List<String> filterName) {
        List<WebElement> categories = baseFunc.getAllElements(FILTER_NAMES);
        List<WebElement> checkboxes = baseFunc.getAllElements(FILTER_CHECKBOX);
        for (int i = 0; i < filterName.size(); i++) {
            for (int j = 0; j < categories.size(); j++) {
                for (int k = 0; k < filterName.size(); k++) {
                    if (categories.get(j).getText().contains(filterName.get(k))) {
                        if (!checkboxes.get(j).isSelected()) {
                            categories.get(j).click();
                            baseFunc.waitJsExecution(FILTER_NAMES);
                            categories = baseFunc.getAllElements(FILTER_NAMES);
                            checkboxes = baseFunc.getAllElements(FILTER_CHECKBOX);
                        }
                    }
                }
            }
        }
    }

    public void collectInformationTxt() throws IOException {
        List<WebElement> information = baseFunc.getAllElements(PRODUCT_INFORMATION);
        File file = new File("C:/Users/Maria/Desktop/sportlandFiles/MenFootballShoes/menFootballShoes.txt");
        FileWriter fw = new FileWriter(file);
        for (WebElement info : information) {
            fw.write(info.getText() + "\r\n");
        }
        fw.close();
    }

    public void createJsonFile() throws FileNotFoundException {
        List<WebElement> information = baseFunc.getAllElements(PRODUCT_INFORMATION);
        File file = new File("C:/Users/Maria/Desktop/sportlandFiles/MenFootballShoes/manFootballshoes.json");
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        for (int i = 0; i < information.size(); i++) {
            list.add(information.get(i).getText() + "\r\n");
        }
        obj.put("Items", list);

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(obj.toJSONString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
