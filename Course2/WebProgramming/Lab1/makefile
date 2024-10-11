# Переменные
JAVAC = javac
JAR = jar
SRC_DIR = src
BIN_DIR = bin
JAR_NAME = example.jar
MAIN_CLASS = Main

# Правило по умолчанию (сборка jar)
all: $(JAR_NAME)

# Правило для создания .jar файла
$(JAR_NAME): $(BIN_DIR)/Main.class $(BIN_DIR)/Example.class
	$(JAR) cfe $(JAR_NAME) $(MAIN_CLASS) -C $(BIN_DIR) .

# Компиляция Main.java
$(BIN_DIR)/Main.class: $(SRC_DIR)/Main.java
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SRC_DIR)/Main.java

# Компиляция Example.java
$(BIN_DIR)/Example.class: $(SRC_DIR)/Example.java
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SRC_DIR)/Example.java

# Очистка временных файлов
clean:
	rm -rf $(BIN_DIR)/*.class $(JAR_NAME)

# Запуск программы
run: $(JAR_NAME)
	java -jar $(JAR_NAME)
