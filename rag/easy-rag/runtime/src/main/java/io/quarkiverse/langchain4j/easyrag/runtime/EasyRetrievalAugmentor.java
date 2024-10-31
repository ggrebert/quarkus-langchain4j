package io.quarkiverse.langchain4j.easyrag.runtime;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Metadata;
import dev.langchain4j.store.embedding.EmbeddingStore;

/**
 * Retrieval augmentor generated automatically by the Easy RAG extension if
 * no other retrieval augmentor is found.
 */
public class EasyRetrievalAugmentor implements RetrievalAugmentor {

    private DefaultRetrievalAugmentor delegate;

    public EasyRetrievalAugmentor(EasyRagConfig config, EmbeddingModel embeddingModel, EmbeddingStore embeddingStore) {
        var contentRetrieverBuilder = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(config.maxResults());

        config.minScore().ifPresent(contentRetrieverBuilder::minScore);

        delegate = DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetrieverBuilder.build()).build();
    }

    @Override
    public UserMessage augment(UserMessage userMessage, Metadata metadata) {
        return delegate.augment(userMessage, metadata);
    }
}
