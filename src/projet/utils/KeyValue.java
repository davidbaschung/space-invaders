package projet.utils;

/**
 * @author David Baschung & Chaimaa Khald
 *
 * class that allocates a value to a given key in an array
 */
public class KeyValue<KeyType, ValueType> {
    private Record[] records;

    public KeyValue(int size) {
        records = new Record[size];//(Record[]) Array.newInstance(Record[].class,size);
    }

    private class Record<KeyType, ValueType> {
        private KeyType key;
        private ValueType value;

        private Record(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(KeyType key, ValueType value) {
        for (int i=0; i<records.length; i++){
            if (records[i]!=null && records[i].key.equals(key))
                break;
            else if (records[i]==null) {
                records[i] = new Record(key,value);
                break;
            }
        }
    }

    public ValueType get(KeyType key) {
        for (int i = 0; i< size(); i++)
            if (records[i].key.equals(key))
                return (ValueType) records[i].value;
        return null;
    }

    public void remove(String key) {
        int size = size();
        for (int i=0; i<size; i++) {
            if ( ((KeyType)records[i]).equals(key) ) {
                records[i] = null;
                for (int j = i; j < size-1; j++) {
                    records[j] = records[j + 1];
                    records[j+1] = null;
                }
                break;
            }
        }
    }

    public int size() {
        int count=0;
        for (int i=0; i<records.length; i++) {
            if (records[i] != null)
                count++;
            else break;
        }
        return count;
    }

    public int maxSize() {
        return records.length;
    }

    @Override
    public KeyValue<KeyType,ValueType> clone() {
        KeyValue<KeyType,ValueType> keyValue = new KeyValue(records.length);
        keyValue.records = this.records.clone();
        return keyValue;
    }

    @Override
    public String toString() {
        String name = getClass().getName();
        name = name.substring(name.lastIndexOf('.', name.length()) + 1, name.length());
        String str = name + "@" + Integer.toHexString(hashCode()) +  ",keys:[";
        for (int i = 0; i< size(); i++)
            str += records[i].key + ",";
        str.substring(0,str.length()-1);
        str += "]\n\tvalues:[";
        for (int i = 0; i< size(); i++)
            str += records[i].value + ",";
        str.substring(0,str.length()-1);
        str += "]";
        return str;
    }
}
