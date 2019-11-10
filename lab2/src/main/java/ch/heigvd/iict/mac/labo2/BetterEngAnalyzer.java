package ch.heigvd.iict.mac.labo2;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created on 10.11.19.
 *
 * @author Max
 *
 * No need for this in the end, this is called a StopAnalyzer.......
 */
public class BetterEngAnalyzer extends StopwordAnalyzerBase {

    public BetterEngAnalyzer() throws IOException {
        super(StopwordAnalyzerBase.loadStopwordSet(Paths.get("documents/cacm.txt")));
    }

    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new ClassicFilter(source);
        result = new EnglishPossessiveFilter(result);
        result = new LowerCaseFilter(result);
        result = new StopFilter(result, stopwords);
        result = new PorterStemFilter(result);
        return new TokenStreamComponents(source, result);
    }

    protected TokenStream normalize(String fieldName, TokenStream in) {
        TokenStream result = new ClassicFilter(in);
        result = new LowerCaseFilter(result);
        return result;
    }
}