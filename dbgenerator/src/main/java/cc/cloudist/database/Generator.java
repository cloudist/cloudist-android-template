package cc.cloudist.database;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class Generator {

    private static final int VERSION = 1000;
    private static final String DEFAULT_PACKAGE_NAME = "cc.cloudist.app.android.template.data.local.database";


    public static void main(String[] args) throws Exception {
        // 初始化数据库相应配置

        Schema schema = new Schema(VERSION, DEFAULT_PACKAGE_NAME);

        addStory(schema);

        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addStory(Schema schema) {
        Entity story = schema.addEntity("Story");
        story.addLongProperty("id").primaryKey().notNull();
        story.addIntProperty("type");
        story.addStringProperty("gaPrefix");
        story.addStringProperty("title");

        Entity image = schema.addEntity("Image");
        image.setTableName("IMAGES");
        image.addIdProperty().primaryKey();
        image.addStringProperty("path");
        Property storyId = image.addLongProperty("storyId").notNull().getProperty();
        image.addToOne(story, storyId);

        ToMany storyToImages = story.addToMany(image, storyId);
        storyToImages.setName("images");
    }

}
