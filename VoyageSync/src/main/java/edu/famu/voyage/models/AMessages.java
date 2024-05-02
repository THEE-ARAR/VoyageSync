@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AMessages {

    @DocumentId
    private @Nullable String messageId;
    private String content;
    private Timestamp timestamp;

    public void setTimestamp(String timestamp) throws ParseException {
        this.timestamp = Timestamp.fromProto(Timestamps.parse(timestamp));
    }
}