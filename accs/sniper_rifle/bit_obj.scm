	� G  ��       �X[o�D~N���d��6����[G
jK�&������]k��ƞM6i#�IQh�
ZTPTAKA�(E��*E�ܤ�_pf�v�⍺ r��3�|�:g.�����f�UuE�^E�|���E÷��<1>1n7������=�L���rwy��n���{ D�u:`x.t��)@�	�����@�p1wv�}N�d��>O�[b=��jճM8}�g/M��9�Q?�Q���c��ryf�e�Μ|����L�pV�u�Q�*�'��|#^�Ķų�7T��8وV�ێ����,1� �Śnzk`@(�<Хy��f��Hw����@wn�FkjF�#�n"_�m�Ab��ZcU�F��IQ�~�m,>�`B���}��t' ��&V�8٦zz>o�R����ǡ����k��� �rlN�o�"%�`5�"��q&~�c��.�cn�n����]I�� �	�;����c<����+d�79�-Y�'&2�<U�D����ٞSI�]����T��Y��{u��߂55�@-7� q�1�aIDS6�D���ɚ\������c��r��U���PLˏ�h�"i�|�b���K���UK��b�F�*���P�#�(�t��Q}�'qLǚ��~H~�ҫ�QQ�L1�#���6r�+�L�t�Ѳ_�I6,����OT�3Yz���z:7�֎�J]�xRb&����lRߕ�蝖Ym �7��FmI��p���"b����gl���*���S�z��	�L����b������?�a��瀵���8 b�|ޯ���y�ll{.�XgCq�p�v��m��k�}#�~v?۽�|�q�yn}va�3����wzh�n��wg��{�:XM/ I�L��e��]_����J��ql{����1Q|�{'ܺ���+`�v��[W��~-�|���|v��+~��u�.E�l�������$a�S�P���9	�MS�"}�Ry��rf��"k�L�~&�Q+�Ȃ��SP�DՒT�TL�0�e�9�sx��3͔*���~&f������'H:D�,��3�C�d%�o�C| dB>���
��P������͞��ჹξ):y��s�&��?sV�5ᢲ�7����T�w�4yF���u_Z ���(�r#0�x:�h� �OhsG�$18�妢�	l�`���o$��]�C�IU%$��Xi0�MSe��؀���CE2�\�ޠ2/�>�l7�����*���� `��ļ)�{O���چ��Z���]�"� ��x)�T(�Z�sI)��������'{;O�Ηa�GXu�m����v���{�@i�nA9�nu�4�H �����A���Q�C+ql�>�vn�ݿ���o��O��$��K�J�?�|�(W"�7�V�U�bI&"��s�`���iL�D	��Q�v-v;3��ճ�x��c����Ym�r��&VoM���Ӓg�&62pƅӿsD����0����Oͱ]D�� l��r?L�EOs*Q �+HU�AQ#�1� �SIb�c�u@Y�3���0%�,�<ϳ1(�9y��;H'�����cAt��\1�!�Ѣ�(��Ƃ�d��/��xXճ�M���ЫH-я����f��*5-P2q7�P����׍2�*�ۡ#`���/-]�O�_�S%�Y��$�s4��w�C�>&�)�}��H�I�4t��vC�j�?p5�3y�kjg.���� {͈�����b�A   �  Lga2/stage/AmpedScript; e MAX CNTMAX start process approachtarget broken lockItemScript (ZZ)V
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
 f-        �L���X� �D /efx/gun/shell_sniper_sub.scm�            ?�  ?�  ?�              � 	hit_bh_s2  d� ,    N  �       ,�>�         (Z)V
 f m end_bit
 f u 1 
stopScript
 f 1 f g  h  - 1  
� ��  i 1  a*� oW*� rL+� +� vW*d� wW*� yW*� |W*�� L*� �W*+� �W*�� wW*� oW=� *� W�W� ���  j 1 �*� �L+� �*� rM+� �>|�~6~6� � 
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
�	W*� �W*X� wW6� *� W�W� `d���  l 1  1*� �W*�W*� rL+� +� �W*�� wW*�W*�W� i�Q                        �  