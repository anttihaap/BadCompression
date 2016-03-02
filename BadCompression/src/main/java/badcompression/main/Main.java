/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.main;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.huffman.HuffmanCompressionByte;
import badcompression.huffman.HuffmanCompressionUTF8;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antti
 */
public class Main {

    public static void main(String[] args) throws Exception {
        args = new String[]{"uncomp", "/home/antti/test.txt.utf8.badComp"};
        if (args.length < 2 || args.length > 3) {
            System.out.println("Argument error.");
            return;
        }

        if ((!args[0].equals("comp") && !args[0].equals("uncomp")) || (args[0].equals("uncomp") && args.length > 2)) {
            System.out.println("Argument error.");
            return;
        }
        String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

        String command = args[0];
        String sourceFilePath;
        boolean utf8 = false;
        if (args.length == 3) {
            sourceFilePath = args[2];
            utf8 = true;
            if (!args[1].equals("utf8")) {
                System.out.println("Argument error: 2nd argument should be UTF8 or empty.");
            }
        } else {
            sourceFilePath = args[1];
        }

        if (command.equals("comp")) {
            //UTF8
            if (utf8) {
                System.out.println(args[2]);
                compress(new HuffmanCompressionUTF8(), args[2], args[2] + ".utf8.badComp");
            } else {
                compress(new HuffmanCompressionByte(), args[1], args[1] + ".badComp");
            }
        } else {
            String[] splitted = args[1].split("\\.");
            if (splitted.length == 1 || !splitted[splitted.length - 1].equals("badComp")) {
                //TODO: check first.
                System.out.println("File not compressed with BadCompression.");
            } else if (splitted[splitted.length - 2].equals("utf8")) {
                System.out.println("UTF8");
                String target = splitted[0];
                for (int i = 1; i < splitted.length - 2; i++) {
                    target += "." + splitted[i];
                }
                System.out.println("target: " + target);
                uncompress(new HuffmanCompressionUTF8(), args[1], target);
                System.out.println("target: " + target);
            } else {
                String target = splitted[0];
                for (int i = 1; i < splitted.length - 1; i++) {
                    target += "." + splitted[i];
                }
                uncompress(new HuffmanCompressionByte(), args[1], target);
                System.out.println("target: " + target);
                System.out.println("normal");

            }

        }
    }

    private static void compress(Compressor comp, String source, String target) {
        File sourceFile = new File(source);
        File targetFile = new File(target);
        checkFiles(targetFile, sourceFile);
        CompressionResults results = null;
        try {
            results = comp.compress(sourceFile, targetFile);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (results.isFailure()) {
            System.out.println("FAILURE:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("File compressed to: " + target);
        }
    }

    private static void uncompress(Compressor comp, String source, String target) {
        File sourceFile = new File(source);
        File targetFile = new File(target);
        checkFiles(targetFile, sourceFile);
        if (targetFile.exists()) {
            System.out.println("FILURE:");
            System.out.println("File " + target + " already exists.");
            return;
        }
        CompressionResults results = null;
        try {
            results = comp.uncompress(sourceFile, targetFile);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (results.isFailure()) {
            System.out.println("Failure:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("File uncompressed to: " + target);

        }
    }

    private static void checkFiles(File target, File source) {
        try {
            if (!target.createNewFile()) {
                System.out.println("FAILURE:");
                System.out.println("Target file " + target + " already excists.");
            } else if (!target.canWrite()) {
                System.out.println("FILURE:");
                System.out.println("Cant write to target file: " + target + ".");
            } else if (!source.getAbsoluteFile().exists()) {
                System.out.println("FAILURE:");
                System.out.println("Source file: " + source + " doesn't excist.");
            } else if (!source.canRead()) {
                System.out.println("FAILURE: ");
                System.out.println("Cant read source file: " + source + ".");
            } else {
                //No failures.
                return;
            }
        } catch (IOException ex) {
            System.out.println("FILURE: cant create file: " + target + ".");
        }
        //Exit program
        System.exit(0);
    }

    private static void printHelp() {
        System.out.println("Usage: ");
        System.out.println("java -jar BadCompression.jar <command> <option for utf8 compression> <file to compress/uncompress>");
        System.out.println("<command>: comp (compress) or uncomp (uncompress)");
        System.out.println("<option for utf compression>: if excists compress file using utf-8");
        System.out.println("TODO^");
    }

}
