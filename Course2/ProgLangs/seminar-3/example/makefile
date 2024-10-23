
JAR_NAME = example.jar
SRC_DIR = src
OUT_DIR = out
SOURCES = $(SRC_DIR)/Main.java $(SRC_DIR)/Example.java
MANIFEST = MANIFEST.MF

all: $(JAR_NAME)

$(JAR_NAME): $(OUT_DIR)/Main.class $(OUT_DIR)/Example.class
	@echo "Создание jar файла..."
	jar cfm $(JAR_NAME) $(MANIFEST) -C $(OUT_DIR) .

$(OUT_DIR)/Main.class: $(SRC_DIR)/Main.java $(SRC_DIR)/Example.java
	@mkdir -p $(OUT_DIR)
	@echo "Компиляция Main.java..."
	javac -d $(OUT_DIR) $(SOURCES)

$(OUT_DIR)/Example.class: $(SRC_DIR)/Example.java
	@mkdir -p $(OUT_DIR)
	@echo "Компиляция Example.java..."
	javac -d $(OUT_DIR) $(SRC_DIR)/Example.java

run:
	java -jar example.jar


clean:
	@echo "Очистка..."
	rm -rf $(OUT_DIR) $(JAR_NAME)

.PHONY: all clean
