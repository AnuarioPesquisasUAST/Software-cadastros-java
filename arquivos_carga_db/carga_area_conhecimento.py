
import csv, sqlite3

import codecs, cStringIO

class UTF8Recoder:
    """
    Iterator that reads an encoded stream and reencodes the input to UTF-8
    """
    def __init__(self, f, encoding):
        self.reader = codecs.getreader(encoding)(f)

    def __iter__(self):
        return self

    def next(self):
        return self.reader.next().encode("utf-8")

class UnicodeReader:
    """
    A CSV reader which will iterate over lines in the CSV file "f",
    which is encoded in the given encoding.
    """

    def __init__(self, f, dialect=csv.excel, encoding="utf-8", **kwds):
        f = UTF8Recoder(f, encoding)
        self.reader = csv.reader(f, dialect=dialect, **kwds)

    def next(self):
        row = self.reader.next()
        return [unicode(s, "utf-8") for s in row]

    def __iter__(self):
        return self

con = sqlite3.connect('anuariodb.sqlite')

file_area_avaliacao = open('areas cnpq.csv', 'r')

sql = 'INSERT INTO areaconhecimento(nome, ciencia, areaAvaliacao) VALUES (?, ?, ?)'

reader = UnicodeReader(file_area_avaliacao, delimiter=',')

for linha in reader:
	ciencia, area_avaliacao, lixo, nome = linha
	con.execute(sql, (nome, ciencia, area_avaliacao))

con.commit()
con.close()