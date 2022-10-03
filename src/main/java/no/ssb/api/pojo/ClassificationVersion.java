package no.ssb.api.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by rsa on 27.10.2016.
 */
@XmlRootElement(name="classificationVersion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassificationVersion {

    @XmlElement(name = "classificationItems")
    private ClassificationItems classificationItems;

    public ClassificationItems getClassificationItems() {
        return classificationItems;
    }

    public void setClassificationItems(ClassificationItems classificationItems) {
        this.classificationItems = classificationItems;
    }

    @Override
    public String toString() {
        return "ClassificationVersion{" +
                "classificationItems=" + classificationItems +
                '}';
    }
}
