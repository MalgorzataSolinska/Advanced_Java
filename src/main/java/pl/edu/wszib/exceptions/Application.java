package pl.edu.wszib.exceptions;

import javax.swing.tree.ExpandVetoException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class Application {
  public static void main(String[] args)  {
     new Application().run();
  }

  private void run()  {
//    try {
//      exampleCheckedExeptionThrow();
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//    exampleUncheckedExeptionThrow();
    try{
      filesExample();
    } catch (UncheckedIOException e) {
      System.out.println(e);
    }
    filesAutoCloseable();
    System.out.println();
    filesAutoCloseable();
  }

  private void filesAutoCloseable() {
    try (FileWriter fileWriter = new FileWriter("test2.txt");) {
      fileWriter.write("test");
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  // wyjatki i hierarchia
  private void filesExample() {
    try {
      byte[] bytes = Files.readAllBytes(Paths.get("test.txt"));
    } catch (NoSuchFileException e) {
      // multicatch
    } catch (IOException | UncheckedIOException e) {
      throw new RuntimeException(e);
      // finally blok wywoływany zawsze
      //niezaleznie czy wyjatek polecial czy nie
      // obsluga zasobów (otwarcie/zamknicie)
      // od java 7 jest interfejs Autocloseable
    } finally {

    }
  }

  private void exampleUncheckedExeptionThrow(){
    throw new RuntimeException("Unchecked exception");
  }

  private void exampleCheckedExeptionThrow() throws Exception{
    throw new Exception("Checked exception");
  }
}
