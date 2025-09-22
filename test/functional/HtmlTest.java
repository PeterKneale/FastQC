package test.functional;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HtmlTest {

    @Test
    public void testHtml() {
        boolean headless = Boolean.parseBoolean(System.getProperty("playwright.headless", "true"));
        try (Playwright pw = Playwright.create()) {
            BrowserType.LaunchOptions opts = new BrowserType.LaunchOptions().setHeadless(headless);
            // Optional: add slowMo in debug, or args, etc.
            Browser browser = pw.chromium().launch(opts);
            BrowserContext ctx = browser.newContext();
            Page page = ctx.newPage();
            page.navigate("https://html5test.com/");
            System.out.println("HTML5 test score: " + page.locator(".score").textContent());
            page.close();
            ctx.close();
            browser.close();
        }
    }
}
