package org.example;

import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DocumentManager manager = new DocumentManager();

        DocumentManager.Author author = DocumentManager.Author.builder()
                .id("author1")
                .name("Ivan Tykhonov")
                .build();

        DocumentManager.Document doc1 = DocumentManager.Document.builder()
                .title("Test doc")
                .content("test text of the document")
                .author(author)
                .created(Instant.now())
                .build();

        manager.save(doc1);

        System.out.println("Saved Documents:");
        manager.findById(doc1.getId()).ifPresent(System.out::println);

        DocumentManager.SearchRequest searchRequest = DocumentManager.SearchRequest.builder()
                .titlePrefixes(List.of("Test"))
                .containsContents(List.of("document"))
                .build();

        List<DocumentManager.Document> searchResults = manager.search(searchRequest);
        System.out.println("Search Results:");
        searchResults.forEach(System.out::println);
    }
}