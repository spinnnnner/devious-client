import java.io.EOFException;
import java.io.IOException;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("sj")
@Implements("BufferedFile")
public class BufferedFile {
   @ObfuscatedName("an")
   @ObfuscatedSignature(
      descriptor = "Lsy;"
   )
   @Export("accessFile")
   AccessFile accessFile;
   @ObfuscatedName("aw")
   @Export("readBuffer")
   byte[] readBuffer;
   @ObfuscatedName("ac")
   @ObfuscatedGetter(
      longValue = 8734338143670189311L
   )
   @Export("readBufferOffset")
   long readBufferOffset = -1L;
   @ObfuscatedName("au")
   @ObfuscatedGetter(
      intValue = -1691135521
   )
   @Export("readBufferLength")
   int readBufferLength;
   @ObfuscatedName("ab")
   @Export("writeBuffer")
   byte[] writeBuffer;
   @ObfuscatedName("aq")
   @ObfuscatedGetter(
      longValue = -8462239109085136939L
   )
   @Export("writeBufferOffset")
   long writeBufferOffset = -1L;
   @ObfuscatedName("al")
   @ObfuscatedGetter(
      intValue = -654786411
   )
   @Export("writeBufferLength")
   int writeBufferLength = 0;
   @ObfuscatedName("at")
   @ObfuscatedGetter(
      longValue = 8429326271465132299L
   )
   @Export("offset")
   long offset;
   @ObfuscatedName("aa")
   @ObfuscatedGetter(
      longValue = 3127984517526915633L
   )
   @Export("fileLength")
   long fileLength;
   @ObfuscatedName("ay")
   @ObfuscatedGetter(
      longValue = -1243507284389482911L
   )
   @Export("length")
   long length;
   @ObfuscatedName("ao")
   @ObfuscatedGetter(
      longValue = 31144933894015367L
   )
   @Export("fileOffset")
   long fileOffset;

   @ObfuscatedSignature(
      descriptor = "(Lsy;II)V"
   )
   public BufferedFile(AccessFile var1, int var2, int var3) throws IOException {
      this.accessFile = var1;
      this.length = this.fileLength = var1.length();
      this.readBuffer = new byte[var2];
      this.writeBuffer = new byte[var3];
      this.offset = 0L;
   }

   @ObfuscatedName("af")
   @ObfuscatedSignature(
      descriptor = "(I)V",
      garbageValue = "1887935555"
   )
   @Export("close")
   public void close() throws IOException {
      this.flush();
      this.accessFile.close();
   }

   @ObfuscatedName("an")
   @Export("seek")
   public void seek(long var1) throws IOException {
      if (var1 < 0L) {
         throw new IOException("");
      } else {
         this.offset = var1;
      }
   }

   @ObfuscatedName("aw")
   @ObfuscatedSignature(
      descriptor = "(S)J",
      garbageValue = "13501"
   )
   @Export("length")
   public long length() {
      return this.length;
   }

   @ObfuscatedName("ac")
   @ObfuscatedSignature(
      descriptor = "([BI)V",
      garbageValue = "421254913"
   )
   @Export("readFully")
   public void readFully(byte[] var1) throws IOException {
      this.read(var1, 0, var1.length);
   }

   @ObfuscatedName("au")
   @ObfuscatedSignature(
      descriptor = "([BIII)V",
      garbageValue = "-1982528940"
   )
   @Export("read")
   public void read(byte[] var1, int var2, int var3) throws IOException {
      try {
         if (var3 + var2 > var1.length) {
            throw new ArrayIndexOutOfBoundsException(var3 + var2 - var1.length);
         }

         if (this.writeBufferOffset != -1L && this.offset >= this.writeBufferOffset && this.offset + (long)var3 <= this.writeBufferOffset + (long)this.writeBufferLength) {
            System.arraycopy(this.writeBuffer, (int)(this.offset - this.writeBufferOffset), var1, var2, var3);
            this.offset += (long)var3;
            return;
         }

         long var4 = this.offset;
         int var7 = var3;
         int var8;
         if (this.offset >= this.readBufferOffset && this.offset < this.readBufferOffset + (long)this.readBufferLength) {
            var8 = (int)((long)this.readBufferLength - (this.offset - this.readBufferOffset));
            if (var8 > var3) {
               var8 = var3;
            }

            System.arraycopy(this.readBuffer, (int)(this.offset - this.readBufferOffset), var1, var2, var8);
            this.offset += (long)var8;
            var2 += var8;
            var3 -= var8;
         }

         if (var3 > this.readBuffer.length) {
            this.accessFile.seek(this.offset);

            for(this.fileOffset = this.offset; var3 > 0; var3 -= var8) {
               var8 = this.accessFile.read(var1, var2, var3);
               if (var8 == -1) {
                  break;
               }

               this.fileOffset += (long)var8;
               this.offset += (long)var8;
               var2 += var8;
            }
         } else if (var3 > 0) {
            this.load();
            var8 = var3;
            if (var3 > this.readBufferLength) {
               var8 = this.readBufferLength;
            }

            System.arraycopy(this.readBuffer, 0, var1, var2, var8);
            var2 += var8;
            var3 -= var8;
            this.offset += (long)var8;
         }

         if (this.writeBufferOffset != -1L) {
            if (this.writeBufferOffset > this.offset && var3 > 0) {
               var8 = var2 + (int)(this.writeBufferOffset - this.offset);
               if (var8 > var3 + var2) {
                  var8 = var3 + var2;
               }

               while(var2 < var8) {
                  var1[var2++] = 0;
                  --var3;
                  ++this.offset;
               }
            }

            long var13 = -1L;
            long var10 = -1L;
            if (this.writeBufferOffset >= var4 && this.writeBufferOffset < var4 + (long)var7) {
               var13 = this.writeBufferOffset;
            } else if (var4 >= this.writeBufferOffset && var4 < this.writeBufferOffset + (long)this.writeBufferLength) {
               var13 = var4;
            }

            if (this.writeBufferOffset + (long)this.writeBufferLength > var4 && (long)this.writeBufferLength + this.writeBufferOffset <= (long)var7 + var4) {
               var10 = this.writeBufferOffset + (long)this.writeBufferLength;
            } else if (var4 + (long)var7 > this.writeBufferOffset && var4 + (long)var7 <= (long)this.writeBufferLength + this.writeBufferOffset) {
               var10 = var4 + (long)var7;
            }

            if (var13 > -1L && var10 > var13) {
               int var12 = (int)(var10 - var13);
               System.arraycopy(this.writeBuffer, (int)(var13 - this.writeBufferOffset), var1, (int)(var13 - var4) + var2, var12);
               if (var10 > this.offset) {
                  var3 = (int)((long)var3 - (var10 - this.offset));
                  this.offset = var10;
               }
            }
         }
      } catch (IOException var16) {
         this.fileOffset = -1L;
         throw var16;
      }

      if (var3 > 0) {
         throw new EOFException();
      }
   }

   @ObfuscatedName("ab")
   @ObfuscatedSignature(
      descriptor = "(B)V",
      garbageValue = "-75"
   )
   @Export("load")
   void load() throws IOException {
      this.readBufferLength = 0;
      if (this.offset != this.fileOffset) {
         this.accessFile.seek(this.offset);
         this.fileOffset = this.offset;
      }

      int var2;
      for(this.readBufferOffset = this.offset; this.readBufferLength < this.readBuffer.length; this.readBufferLength += var2) {
         int var1 = this.readBuffer.length - this.readBufferLength;
         if (var1 > 200000000) {
            var1 = 200000000;
         }

         var2 = this.accessFile.read(this.readBuffer, this.readBufferLength, var1);
         if (var2 == -1) {
            break;
         }

         this.fileOffset += (long)var2;
      }

   }

   @ObfuscatedName("aq")
   @ObfuscatedSignature(
      descriptor = "([BIIB)V",
      garbageValue = "3"
   )
   @Export("write")
   public void write(byte[] var1, int var2, int var3) throws IOException {
      try {
         if (this.offset + (long)var3 > this.length) {
            this.length = (long)var3 + this.offset;
         }

         if (this.writeBufferOffset != -1L && (this.offset < this.writeBufferOffset || this.offset > this.writeBufferOffset + (long)this.writeBufferLength)) {
            this.flush();
         }

         if (this.writeBufferOffset != -1L && (long)var3 + this.offset > this.writeBufferOffset + (long)this.writeBuffer.length) {
            int var4 = (int)((long)this.writeBuffer.length - (this.offset - this.writeBufferOffset));
            System.arraycopy(var1, var2, this.writeBuffer, (int)(this.offset - this.writeBufferOffset), var4);
            this.offset += (long)var4;
            var2 += var4;
            var3 -= var4;
            this.writeBufferLength = this.writeBuffer.length;
            this.flush();
         }

         if (var3 <= this.writeBuffer.length) {
            if (var3 > 0) {
               if (-1L == this.writeBufferOffset) {
                  this.writeBufferOffset = this.offset;
               }

               System.arraycopy(var1, var2, this.writeBuffer, (int)(this.offset - this.writeBufferOffset), var3);
               this.offset += (long)var3;
               if (this.offset - this.writeBufferOffset > (long)this.writeBufferLength) {
                  this.writeBufferLength = (int)(this.offset - this.writeBufferOffset);
               }

            }
         } else {
            if (this.fileOffset != this.offset) {
               this.accessFile.seek(this.offset);
               this.fileOffset = this.offset;
            }

            this.accessFile.write(var1, var2, var3);
            this.fileOffset += (long)var3;
            if (this.fileOffset > this.fileLength) {
               this.fileLength = this.fileOffset;
            }

            long var9 = -1L;
            long var6 = -1L;
            if (this.offset >= this.readBufferOffset && this.offset < (long)this.readBufferLength + this.readBufferOffset) {
               var9 = this.offset;
            } else if (this.readBufferOffset >= this.offset && this.readBufferOffset < (long)var3 + this.offset) {
               var9 = this.readBufferOffset;
            }

            if (this.offset + (long)var3 > this.readBufferOffset && (long)var3 + this.offset <= (long)this.readBufferLength + this.readBufferOffset) {
               var6 = this.offset + (long)var3;
            } else if ((long)this.readBufferLength + this.readBufferOffset > this.offset && this.readBufferOffset + (long)this.readBufferLength <= this.offset + (long)var3) {
               var6 = this.readBufferOffset + (long)this.readBufferLength;
            }

            if (var9 > -1L && var6 > var9) {
               int var8 = (int)(var6 - var9);
               System.arraycopy(var1, (int)((long)var2 + var9 - this.offset), this.readBuffer, (int)(var9 - this.readBufferOffset), var8);
            }

            this.offset += (long)var3;
         }
      } catch (IOException var12) {
         this.fileOffset = -1L;
         throw var12;
      }
   }

   @ObfuscatedName("al")
   @ObfuscatedSignature(
      descriptor = "(I)V",
      garbageValue = "789688414"
   )
   @Export("flush")
   void flush() throws IOException {
      if (this.writeBufferOffset != -1L) {
         if (this.fileOffset != this.writeBufferOffset) {
            this.accessFile.seek(this.writeBufferOffset);
            this.fileOffset = this.writeBufferOffset;
         }

         this.accessFile.write(this.writeBuffer, 0, this.writeBufferLength);
         this.fileOffset += -654786411L * (long)(this.writeBufferLength * -1669068099);
         if (this.fileOffset > this.fileLength) {
            this.fileLength = this.fileOffset;
         }

         long var1 = -1L;
         long var3 = -1L;
         if (this.writeBufferOffset >= this.readBufferOffset && this.writeBufferOffset < (long)this.readBufferLength + this.readBufferOffset) {
            var1 = this.writeBufferOffset;
         } else if (this.readBufferOffset >= this.writeBufferOffset && this.readBufferOffset < (long)this.writeBufferLength + this.writeBufferOffset) {
            var1 = this.readBufferOffset;
         }

         if (this.writeBufferOffset + (long)this.writeBufferLength > this.readBufferOffset && (long)this.writeBufferLength + this.writeBufferOffset <= (long)this.readBufferLength + this.readBufferOffset) {
            var3 = this.writeBufferOffset + (long)this.writeBufferLength;
         } else if (this.readBufferOffset + (long)this.readBufferLength > this.writeBufferOffset && (long)this.readBufferLength + this.readBufferOffset <= this.writeBufferOffset + (long)this.writeBufferLength) {
            var3 = (long)this.readBufferLength + this.readBufferOffset;
         }

         if (var1 > -1L && var3 > var1) {
            int var5 = (int)(var3 - var1);
            System.arraycopy(this.writeBuffer, (int)(var1 - this.writeBufferOffset), this.readBuffer, (int)(var1 - this.readBufferOffset), var5);
         }

         this.writeBufferOffset = -1L;
         this.writeBufferLength = 0;
      }

   }
}
