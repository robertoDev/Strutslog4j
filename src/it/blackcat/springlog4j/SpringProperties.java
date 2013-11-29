package it.blackcat.springlog4j;

/**
 * Created by PGB on 29/11/13.
 */
public class SpringProperties {
    private String exportPath;
    private String importPath;

    public String getImportPath() {
        return importPath;
    }

    public void setImportPath(String importPath) {
        this.importPath = importPath;
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }
}
