	� G  ��       �X�o�TN���L&!gM}˥�2W*+��؅����r�Ċc��I�v�Dҡ�Mlh `h���61��M��_�ڍ���c��8�2�����}���k��6F��sU]|�W���h"s��&.MNLNX���a��b׳SǺp�4�]En#�����=��:0\:���K��D�OU�j8�;��>�rr��>O�[f=��j͵L8=�g.MN�lר�ƨ����BY�<3����3'�ecg]�\��8Ш\��A��/Qb�³�7T�eۙ�V�[�������,1jᥚn��`@(�<Хy��!f��Pw����@wn�B�jZ��}�j"O󬊍Ĳ��ƚ��ti��6ҽX�H|����B\S�m|C�HY�d���z��J%�:�fj��7��\(�ؚ���D<Jh�jTEg�*L��G~�]*���!��������A��sE�[�;�!f�xB3??��porR[�����p[�T!�D� -���J��eF7���peϭ#����[بj�������J"��a$ҍ5M�䂤�4�L�=��K�2TAˑ�|!,7��Ɋ��rɊ��V,'ir2Va\,E�U�6&C͌m�D���f��}��1knL,���%K�GEIN0y��]V���ɯ�%2�����~�&٨h�c�?Qm8�LT�[6>�׹ճv�U�2Ǔ3%g��g����@��j9x�	�X7j˺�֨���%$"?c9&?��S�徒�_��Pf�H�;���~���O?�d�� ����^u����oa�u���:�(۷��������`�Q�y�l�����A�Q��8��:��g�ll���to��wg��{�:T���V����Կ��ı�)	��0����+�K)Q|�{'�^{��0^:׃��g�_:_���7���A�ʟ> C���K1[�$�%!W�r�0�iI�WF�91��P�}yr�rf��kr�L�A&�Q+�Ȃ��SR�HՒTT��0�e�9�st��3Ô*���A&f�����= H:D�,'�3�C�d%�o�C| eB.����C��R�<��F�;��ჹξ):y���&��?sV�5ᢲ�76���T�w�4yF��uO�[e ���(FrC0n�:�h� �OhsG�$�8��ÑM�-�`��o��C�S=�#�IU%$�Hi0�MSe��؀���AE2{\�ޠ2/Z�L/�����*��,� `��ļ)�{O���چ��Z����"���y)�T(�Z�uH)��������'{;O�ΗA�GXu�e���v���{�@i�t���(�}�n�.�uбȃ9Yj��j۽Oo�{����Cy��$=I%���Bϥ������Uz�*�A���H��l4ش��%E*R�B|�{�]��NGpbu+�h�d�cR�ش�*m�b�~M���$ke�%�	Ml����J��a�A��m9�����Ts�A�,��f�� �W�����F�#�!,��R�X7ꀲBgYmaJY��.^`cP���,�w�N,e�{�Ƃ�Pm9�_C���WPٍ�M_�7t�gJ[4�M��W�*�*��Y�7�jZ�\�|%u�U�����pݨ!����:Fy����Em���e:W"���K=K�}p;<9��cJ����K߀D��K#��IWT���wS}�9�V�pz�&(��J��m��tc�7 �֩    �  Lga2/stage/AmpedScript; e MAX CNTMAX start process approachtarget broken lockItemScript (ZZ)V
 f m n getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d p q Lkotori/gfx/KGLScriptModel; s kill
 t u 1
 H 0 3 setShadowState
 d x 3 tempInt (I)Ljava/lang/Integer;
 d z { generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d } ~D /accs/sniper_rifle/bit_mv.scm�            ?�  ?�  ?�               clearGenerate
 d � 1 setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � � temp ()Ljava/lang/Object;
 d � � Ljava/lang/Integer; � intValue
 � � 2��� startScript (Ljava/lang/String;)Z
 t � � mv_1_150_60 mv_15_315_345 mv_2_120_330 mv_15_330_10 mv_15_210_10 mv_15_60_30 mv_15_300_300 mv_2_135_340 mv_2_330_60 mv_15_330_225 mv_15_300_350 mv_15_240_150 mv_1_210_300 mv_15_240_30 setJudgmentApproachTarget (F)V
 f � �A    clearJudgmentKind
 f � 1 getArgv
 f � � getPosition ()Lkotori/geom/Position;
 f � � ()Lkotori/geom/Vector3D;
 H  �  (FFF)Lkotori/geom/Vector3D;
 � + �>���?fff�����333?��?L���   ����=����L�����?333>L��?����L��>������� .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � + � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY (F)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/Position; � dir	 � � 
 � ) � sub
 � � � cos
 H � � sin
 H � �	 � Q 	 � S 	 � R  getDir (Lkotori/geom/Vector3D;)F
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � start2 rotate (Lkotori/geom/Quaternion;I)V
 t � � vibe (FFFFI)V
 d � �<#�
B�   	playSound (IIF)V
 d � � mix 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � �D /efx/flash/glow01_blu19.ptc�            ?�  ?�  ?�               D /efx/flash/glow01_blu19.ptc�            ?�  ?�  ?�               D /efx/flash/glow01_blu19.ptc�            ?�  ?�  ?�                A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d } D /efx/flash/flash_line.scm�            ?�  ?�  @@                	start_lb2D /efx/flash/flash_line.scm�            ?�  ?�  @@               D /efx/ripple/ripple01.scm�            ?�  ?�  ?�                lb attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f-        �L���� �D /efx/gun/shell_sniper_sub.scm�            ?�  ?�  ?�              � 	hit_bh_s2  d� ,    N  �       ,�>�         (Z)V
 f m end_bit
 f u 1 
stopScript
 f 1 f g  h  - 1  � ��  i 1  a*� oW*� rL+� +� vW*d� wW*� yW*� |W*�� L*� �W*+� �W*�� wW*� oW=� *� W�W� ���  j 1 �*� �L+� �*� rM+� �>|�~6~6� � 
�� *� W�     Y    h    w    �    �    �    �    �   	 �   
 �    �    �      +,� 
,�� �W� �,� 
,�� �W� �,� 
,�� �W� �,� 
,�� �W� �,� 
,�� �W� �,� 
,�� �W� ~,� 
,�� �W� o,� 
,�� �W� `,� 
,�� �W� Q,� 
,�� �W� B,� 
,�� �W� 3,� 
,�� �W� $,� 
,�� �W� ,� 
,�� �W� � *`x�� |W* ȶ wW*�� �W*X� wW*� �W* ȶ wW� d� *� W�  k 1 �*� �L*� �M� �N*� �:� �6|�~6~6*`x�� |Wd�     Y    g    u    �    �    �    �    �   	 �   
 �    �    �    �   -���� �W� �-�b�� �W� �-���� �W� �-��� �W� �-���� �W� �-b��� �W� u-���� �W� g-���� �W� Y-��b� �W� K-��� �W� >-���� �W� 0-bb�� �W� "-��b� �W� -���� �W� � � �:-� �W,� ͸ ƶ �W,� �W+,� �W+� �,� ͸ �j+� �,� ͸ �jf8	+� �,� ͸ �j+� �,� ͸ �jb8
+� �8+	
� �W+� ڸ Ƹ ߆8+� �� � Ƹ �Zd�8�:� �:� �W*� r:� �� �W� �W* ȶ wW*��� ȶ �W* �b� �W� �� �W*-� �W*�� W*�� W*�� W*�W*�W*�W*
�	W*� �W*X� wW6� *� W�W� `d���  l 1  1*� �W*�W*� rL+� +� �W*�� wW*�W*�W� i��                        �  