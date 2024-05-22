package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import abstractions.PageBase;
import interfaces.UnambiguousPageInterface;
import utils.DateFormatType;

public class PreferencesPage extends PageBase<PreferencesPage> implements UnambiguousPageInterface<PreferencesPage> {
    private By apperanceTabLocator = By.xpath("//div[@id='ooui-php-502']//span");
    private By saveButtonLocator = By.xpath("//span[@id='prefcontrol']//button");
    private String monthDayYearRadioButtonId = "ooui-php-85";
    private String dayMonthYearRadioButtonId = "ooui-php-86";

    public PreferencesPage(WebDriver driver) {
        super(driver);
        this.ensure();
    }

    public PreferencesPage selectApperancePreferencesTab() {
        WebElement apperanceTab = waitAndReturnElement(apperanceTabLocator);
        (new Actions(this.driver)).moveToElement(apperanceTab).click().perform();
        return this;
    }

    public PreferencesPage changeDateFormatPreference(DateFormatType[] formatType) {
        if (!this.checkIfIsSelectedById(monthDayYearRadioButtonId)) {
            this.clickById(monthDayYearRadioButtonId);
            formatType[0] = DateFormatType.MONTH_DAY_YEAR;
            return this;
        } else if (!this.checkIfIsSelectedById(dayMonthYearRadioButtonId)) {
            this.clickById(dayMonthYearRadioButtonId);
            formatType[0] = DateFormatType.DAY_MONTH_YEAR; 
            return this;
        }

        formatType[0] = DateFormatType.NO_PREFERENCE;
        return this;
    }

    public PreferencesPage savePreferences() {
        waitAndReturnElement(saveButtonLocator).click();
        return this.hold(5);
    }

    public PreferencesPage ensure() {
        waitElement(apperanceTabLocator);
        return this;
    }
}
