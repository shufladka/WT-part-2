package by.bsuir.dao.mapper;

public class RecordMapperFactory {
    private static RecordMapperFactory INSTANCE;

    private RecordMapperFactory() {}

    public static RecordMapperFactory getInstance()
    {
        if (INSTANCE == null) {
            synchronized (RecordMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RecordMapperFactory();
                }
            }
        }

        return INSTANCE;
    }


}
