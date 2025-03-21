import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("lz")
@Implements("MusicPatch")
public class MusicPatch extends Node {
   @ObfuscatedName("af")
   @ObfuscatedGetter(
      intValue = -1979814511
   )
   int field3437;
   @ObfuscatedName("an")
   @ObfuscatedSignature(
      descriptor = "[Lbr;"
   )
   @Export("rawSounds")
   RawSound[] rawSounds = new RawSound[128];
   @ObfuscatedName("aw")
   short[] field3436 = new short[128];
   @ObfuscatedName("ac")
   byte[] field3438 = new byte[128];
   @ObfuscatedName("au")
   byte[] field3439 = new byte[128];
   @ObfuscatedName("ab")
   @ObfuscatedSignature(
      descriptor = "[Llp;"
   )
   MusicPatchNode2[] field3440 = new MusicPatchNode2[128];
   @ObfuscatedName("aq")
   byte[] field3441 = new byte[128];
   @ObfuscatedName("al")
   int[] field3442 = new int[128];

   MusicPatch(byte[] var1) {
      Buffer var2 = new Buffer(var1);

      int var3;
      for(var3 = 0; var2.array[var3 + var2.offset] != 0; ++var3) {
      }

      byte[] var4 = new byte[var3];

      int var5;
      for(var5 = 0; var5 < var3; ++var5) {
         var4[var5] = var2.readByte();
      }

      ++var2.offset;
      ++var3;
      var5 = var2.offset;
      var2.offset += var3;

      int var6;
      for(var6 = 0; var2.array[var6 + var2.offset] != 0; ++var6) {
      }

      byte[] var7 = new byte[var6];

      int var8;
      for(var8 = 0; var8 < var6; ++var8) {
         var7[var8] = var2.readByte();
      }

      ++var2.offset;
      ++var6;
      var8 = var2.offset;
      var2.offset += var6;

      int var9;
      for(var9 = 0; var2.array[var9 + var2.offset] != 0; ++var9) {
      }

      byte[] var10 = new byte[var9];

      for(int var11 = 0; var11 < var9; ++var11) {
         var10[var11] = var2.readByte();
      }

      ++var2.offset;
      ++var9;
      byte[] var38 = new byte[var9];
      int var12;
      int var14;
      if (var9 > 1) {
         var38[1] = 1;
         int var13 = 1;
         var12 = 2;

         for(var14 = 2; var14 < var9; ++var14) {
            int var43 = var2.readUnsignedByte();
            if (var43 == 0) {
               var13 = var12++;
            } else {
               if (var43 <= var13) {
                  --var43;
               }

               var13 = var43;
            }

            var38[var14] = (byte)var13;
         }
      } else {
         var12 = var9;
      }

      MusicPatchNode2[] var39 = new MusicPatchNode2[var12];

      MusicPatchNode2 var15;
      for(var14 = 0; var14 < var39.length; ++var14) {
         var15 = var39[var14] = new MusicPatchNode2();
         int var42 = var2.readUnsignedByte();
         if (var42 > 0) {
            var15.field3374 = new byte[var42 * 2];
         }

         var42 = var2.readUnsignedByte();
         if (var42 > 0) {
            var15.field3373 = new byte[var42 * 2 + 2];
            var15.field3373[1] = 64;
         }
      }

      var14 = var2.readUnsignedByte();
      byte[] var44 = var14 > 0 ? new byte[var14 * 2] : null;
      var14 = var2.readUnsignedByte();
      byte[] var16 = var14 > 0 ? new byte[var14 * 2] : null;

      int var17;
      for(var17 = 0; var2.array[var17 + var2.offset] != 0; ++var17) {
      }

      byte[] var18 = new byte[var17];

      int var19;
      for(var19 = 0; var19 < var17; ++var19) {
         var18[var19] = var2.readByte();
      }

      ++var2.offset;
      ++var17;
      var19 = 0;

      int var20;
      for(var20 = 0; var20 < 128; ++var20) {
         var19 += var2.readUnsignedByte();
         this.field3436[var20] = (short)var19;
      }

      var19 = 0;

      short[] var48;
      for(var20 = 0; var20 < 128; ++var20) {
         var19 += var2.readUnsignedByte();
         var48 = this.field3436;
         var48[var20] = (short)(var48[var20] + (var19 << 8));
      }

      var20 = 0;
      int var21 = 0;
      int var22 = 0;

      int var23;
      for(var23 = 0; var23 < 128; ++var23) {
         if (var20 == 0) {
            if (var21 < var18.length) {
               var20 = var18[var21++];
            } else {
               var20 = -1;
            }

            var22 = var2.readVarInt();
         }

         var48 = this.field3436;
         var48[var23] = (short)(var48[var23] + ((var22 - 1 & 2) << 14));
         this.field3442[var23] = var22;
         --var20;
      }

      var20 = 0;
      var21 = 0;
      var23 = 0;

      int var24;
      for(var24 = 0; var24 < 128; ++var24) {
         if (this.field3442[var24] != 0) {
            if (var20 == 0) {
               if (var21 < var4.length) {
                  var20 = var4[var21++];
               } else {
                  var20 = -1;
               }

               var23 = var2.array[var5++] - 1;
            }

            this.field3441[var24] = (byte)var23;
            --var20;
         }
      }

      var20 = 0;
      var21 = 0;
      var24 = 0;

      for(int var25 = 0; var25 < 128; ++var25) {
         if (this.field3442[var25] != 0) {
            if (var20 == 0) {
               if (var21 < var7.length) {
                  var20 = var7[var21++];
               } else {
                  var20 = -1;
               }

               var24 = var2.array[var8++] + 16 << 2;
            }

            this.field3439[var25] = (byte)var24;
            --var20;
         }
      }

      var20 = 0;
      var21 = 0;
      MusicPatchNode2 var40 = null;

      int var26;
      for(var26 = 0; var26 < 128; ++var26) {
         if (this.field3442[var26] != 0) {
            if (var20 == 0) {
               var40 = var39[var38[var21]];
               if (var21 < var10.length) {
                  var20 = var10[var21++];
               } else {
                  var20 = -1;
               }
            }

            this.field3440[var26] = var40;
            --var20;
         }
      }

      var20 = 0;
      var21 = 0;
      var26 = 0;

      int var27;
      for(var27 = 0; var27 < 128; ++var27) {
         if (var20 == 0) {
            if (var21 < var18.length) {
               var20 = var18[var21++];
            } else {
               var20 = -1;
            }

            if (this.field3442[var27] > 0) {
               var26 = var2.readUnsignedByte() + 1;
            }
         }

         this.field3438[var27] = (byte)var26;
         --var20;
      }

      this.field3437 = var2.readUnsignedByte() + 1;

      int var29;
      MusicPatchNode2 var41;
      for(var27 = 0; var27 < var12; ++var27) {
         var41 = var39[var27];
         if (var41.field3374 != null) {
            for(var29 = 1; var29 < var41.field3374.length; var29 += 2) {
               var41.field3374[var29] = var2.readByte();
            }
         }

         if (var41.field3373 != null) {
            for(var29 = 3; var29 < var41.field3373.length - 2; var29 += 2) {
               var41.field3373[var29] = var2.readByte();
            }
         }
      }

      if (var44 != null) {
         for(var27 = 1; var27 < var44.length; var27 += 2) {
            var44[var27] = var2.readByte();
         }
      }

      if (var16 != null) {
         for(var27 = 1; var27 < var16.length; var27 += 2) {
            var16[var27] = var2.readByte();
         }
      }

      for(var27 = 0; var27 < var12; ++var27) {
         var41 = var39[var27];
         if (var41.field3373 != null) {
            var19 = 0;

            for(var29 = 2; var29 < var41.field3373.length; var29 += 2) {
               var19 = var19 + 1 + var2.readUnsignedByte();
               var41.field3373[var29] = (byte)var19;
            }
         }
      }

      for(var27 = 0; var27 < var12; ++var27) {
         var41 = var39[var27];
         if (var41.field3374 != null) {
            var19 = 0;

            for(var29 = 2; var29 < var41.field3374.length; var29 += 2) {
               var19 = 1 + var19 + var2.readUnsignedByte();
               var41.field3374[var29] = (byte)var19;
            }
         }
      }

      int var28;
      int var30;
      int var31;
      int var32;
      int var33;
      int var34;
      int var35;
      int var36;
      byte var46;
      if (var44 != null) {
         var19 = var2.readUnsignedByte();
         var44[0] = (byte)var19;

         for(var27 = 2; var27 < var44.length; var27 += 2) {
            var19 = 1 + var19 + var2.readUnsignedByte();
            var44[var27] = (byte)var19;
         }

         var46 = var44[0];
         var28 = var44[1];

         for(var29 = 0; var29 < var46; ++var29) {
            this.field3438[var29] = (byte)(var28 * this.field3438[var29] + 32 >> 6);
         }

         for(var29 = 2; var29 < var44.length; var29 += 2) {
            var30 = var44[var29];
            var31 = var44[var29 + 1];
            var32 = var28 * (var30 - var46) + (var30 - var46) / 2;

            for(var33 = var46; var33 < var30; ++var33) {
               var35 = var30 - var46;
               var36 = var32 >>> 31;
               var34 = (var32 + var36) / var35 - var36;
               this.field3438[var33] = (byte)(var34 * this.field3438[var33] + 32 >> 6);
               var32 += var31 - var28;
            }

            var46 = (byte)var30;
            var28 = var31;
         }

         for(var30 = var46; var30 < 128; ++var30) {
            this.field3438[var30] = (byte)(var28 * this.field3438[var30] + 32 >> 6);
         }

         var15 = null;
      }

      if (var16 != null) {
         var19 = var2.readUnsignedByte();
         var16[0] = (byte)var19;

         for(var27 = 2; var27 < var16.length; var27 += 2) {
            var19 = 1 + var19 + var2.readUnsignedByte();
            var16[var27] = (byte)var19;
         }

         var46 = var16[0];
         var28 = var16[1] << 1;

         for(var29 = 0; var29 < var46; ++var29) {
            var30 = var28 + (this.field3439[var29] & 255);
            if (var30 < 0) {
               var30 = 0;
            }

            if (var30 > 128) {
               var30 = 128;
            }

            this.field3439[var29] = (byte)var30;
         }

         for(var29 = 2; var29 < var16.length; var29 += 2) {
            byte var47 = var16[var29];
            var31 = var16[var29 + 1] << 1;
            var32 = var28 * (var47 - var46) + (var47 - var46) / 2;

            for(var33 = var46; var33 < var47; ++var33) {
               var35 = var47 - var46;
               var36 = var32 >>> 31;
               var34 = (var32 + var36) / var35 - var36;
               int var37 = var34 + (this.field3439[var33] & 255);
               if (var37 < 0) {
                  var37 = 0;
               }

               if (var37 > 128) {
                  var37 = 128;
               }

               this.field3439[var33] = (byte)var37;
               var32 += var31 - var28;
            }

            var46 = var47;
            var28 = var31;
         }

         for(var30 = var46; var30 < 128; ++var30) {
            var31 = var28 + (this.field3439[var30] & 255);
            if (var31 < 0) {
               var31 = 0;
            }

            if (var31 > 128) {
               var31 = 128;
            }

            this.field3439[var30] = (byte)var31;
         }

         Object var45 = null;
      }

      for(var27 = 0; var27 < var12; ++var27) {
         var39[var27].field3375 = var2.readUnsignedByte();
      }

      for(var27 = 0; var27 < var12; ++var27) {
         var41 = var39[var27];
         if (var41.field3374 != null) {
            var41.field3376 = var2.readUnsignedByte();
         }

         if (var41.field3373 != null) {
            var41.field3381 = var2.readUnsignedByte();
         }

         if (var41.field3375 > 0) {
            var41.field3378 = var2.readUnsignedByte();
         }
      }

      for(var27 = 0; var27 < var12; ++var27) {
         var39[var27].field3380 = var2.readUnsignedByte();
      }

      for(var27 = 0; var27 < var12; ++var27) {
         var41 = var39[var27];
         if (var41.field3380 > 0) {
            var41.field3379 = var2.readUnsignedByte();
         }
      }

      for(var27 = 0; var27 < var12; ++var27) {
         var41 = var39[var27];
         if (var41.field3379 > 0) {
            var41.field3377 = var2.readUnsignedByte();
         }
      }

   }

   @ObfuscatedName("af")
   @ObfuscatedSignature(
      descriptor = "(Lba;[B[II)Z",
      garbageValue = "875110520"
   )
   boolean method5937(SoundCache var1, byte[] var2, int[] var3) {
      boolean var4 = true;
      int var5 = 0;
      RawSound var6 = null;

      for(int var7 = 0; var7 < 128; ++var7) {
         if (var2 == null || var2[var7] != 0) {
            int var8 = this.field3442[var7];
            if (var8 != 0) {
               if (var8 != var5) {
                  var5 = var8--;
                  if ((var8 & 1) == 0) {
                     var6 = var1.getSoundEffect(var8 >> 2, var3);
                  } else {
                     var6 = var1.getMusicSample(var8 >> 2, var3);
                  }

                  if (var6 == null) {
                     var4 = false;
                  }
               }

               if (var6 != null) {
                  this.rawSounds[var7] = var6;
                  this.field3442[var7] = 0;
               }
            }
         }
      }

      return var4;
   }

   @ObfuscatedName("an")
   @ObfuscatedSignature(
      descriptor = "(B)V",
      garbageValue = "-21"
   )
   @Export("clear")
   void clear() {
      this.field3442 = null;
   }
}
