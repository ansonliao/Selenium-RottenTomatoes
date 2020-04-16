package example;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Image;
import ru.yandex.qatools.htmlelements.element.Link;

@FindBy(css = ".mb-movie")
@Name("Movie Card")
public class MovieCard extends HtmlElement {

    @FindBy(css = ".poster")
    @Name("Movie Poster")
    public Image poster;

    @FindBy(css = ".movieTitle")
    @Name("Movie Title")
    public Link title;

    @FindBy(css = ".tMeterScore")
    @Name("Movie Score")
    public Link score;
}
