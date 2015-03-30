# distributed-systems-parallel-matrix-multiplication
Matris çarpımını farklı makinalarda paralel olarak işletip tek bir sonuç matrisi haline getiren uygulama.
Matris çarpımının mimarisi dağıtık sistemler dersinde öğrenilen master-slave ilişkisi ile gerçekleştirilmiştir.
AxB = X matris çarpımında A matrisinin i. satırı ile B matrisinin j. sütununu master bilgisayarda alıp boş olan sıradaki slave bilgisayara gönderilip orada işletildikten sonra X sonuç matrisinin i,j hücresine sonuç yazılır.
  Her satır ve sütün matrisleri sırada olan boş slave bilgisayara gönderilir.
