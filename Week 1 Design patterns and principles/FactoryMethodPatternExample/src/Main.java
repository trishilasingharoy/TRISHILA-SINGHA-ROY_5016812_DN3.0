// Document.java
interface Document {
    void open();
    void save();
}

// WordDocument.java
class WordDocument implements Document {

    public void open() {
        System.out.println("Opening Word document...");
    }


    public void save() {
        System.out.println("Saving Word document...");
    }
}

// PdfDocument.java
class PdfDocument implements Document {

    public void open() {
        System.out.println("Opening PDF document...");
    }


    public void save() {
        System.out.println("Saving PDF document...");
    }
}

// ExcelDocument.java
class ExcelDocument implements Document {

    public void open() {
        System.out.println("Opening Excel document...");
    }


    public void save() {
        System.out.println("Saving Excel document...");
    }
}

// DocumentFactory.java
abstract class DocumentFactory {
    public abstract Document createDocument();
}

// WordDocumentFactory.java
class WordDocumentFactory extends DocumentFactory {

    public Document createDocument() {
        return new WordDocument();
    }
}

// PdfDocumentFactory.java
class PdfDocumentFactory extends DocumentFactory {

    public Document createDocument() {
        return new PdfDocument();
    }
}

// ExcelDocumentFactory.java
class ExcelDocumentFactory extends DocumentFactory {

    public Document createDocument() {
        return new ExcelDocument();
    }
}

// FactoryMethodPatternTest.java
class FactoryMethodPatternTest {
    public static void main(String[] args) {
        // Create factories
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // Create documents
        Document wordDoc = wordFactory.createDocument();
        Document pdfDoc = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();

        // Use documents
        wordDoc.open();
        wordDoc.save();

        pdfDoc.open();
        pdfDoc.save();

        excelDoc.open();
        excelDoc.save();
    }
}
