package br.com.mvsouza.scraping;

import br.com.mvsouza.dto.TrackingRequest;
import br.com.mvsouza.dto.TrackingResponse;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ScrapingService {

    public List<TrackingResponse> execute(TrackingRequest request) throws Exception {

        WebDriver driver = null;

        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, 15);

            driver.get("https://www.directlog.com.br");

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-src='#janela_rastreie']"))).click();

            WebElement trackingTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("tipo_rastreio")));
            new Select(trackingTypeElement).selectByValue(request.getType().getComboValue());

            wait.until(ExpectedConditions.elementToBeClickable(By.id("var_individual"))).sendKeys(request.getCode());

            wait.until(ExpectedConditions.elementToBeClickable(By.className("btn_envia"))).click();

            List<WebElement> resultRows = driver.findElements(By.cssSelector("table > tbody > tr[style*=font]"));

            return resultRows.stream().map(row -> {
                List<String> colsValues = row.findElements(By.tagName("td"))
                        .stream()
                        .filter(Objects::nonNull)
                        .map(WebElement::getText)
                        .collect(Collectors.toList());

                return TrackingResponse.builder()
                        .datetime(String.format("%s %s", colsValues.get(0), colsValues.get(1)))
                        .description(colsValues.get(2))
                        .city(colsValues.get(3))
                        .observations(colsValues.get(4))
                        .build();
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Erro no scraping", e);
            return Collections.emptyList();
        } finally {
            if (driver != null) {
                driver.close();
            }
        }
    }


}
