import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final MeterRegistry meterRegistry;

    public BookController(BookRepository bookRepository, MeterRegistry meterRegistry) {
        this.bookRepository = bookRepository;
        this.meterRegistry = meterRegistry;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        bookRepository.save(book);
        meterRegistry.counter("books.created.count").increment();
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
}
