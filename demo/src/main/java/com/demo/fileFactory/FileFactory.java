package com.demo.fileFactory;

interface FileFactory {
    void provideTemplateData(String tableName);
    void provideFileInstance();
    void createFileFields();
    void fillFileWithRegisters();
    String generateFullFilledFile();
    String getCustomHeaderByTemplate();
    String getFileType();
}
