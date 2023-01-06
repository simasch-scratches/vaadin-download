package ch.martinelli.demo.vaadin.views.helloworld;

import ch.martinelli.demo.vaadin.views.MainLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    public HelloWorldView(@Value("${pdf.file}") String filename) {
        Anchor download = new Anchor(new StreamResource("RG-1007150-8949.pdf",
                () -> {
                    byte[] fileContent;
                    try {
                        fileContent = Files.readAllBytes(new File(filename).toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return new ByteArrayInputStream(fileContent);
                }), "Download");
        download.getElement().setAttribute("download", true);

        add(download);
    }
}
