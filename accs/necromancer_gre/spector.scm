	� G   t�       �X{oG�ۑ�O?�TtN��#Ї#%���WJP������g_s�;��!�D����G�
-�%�5�QUj!��l��R?Dgw���� �e������;����36�J*KѲ�2��.MMŰ��]J��MM�n*b�U�w3Y��RC]�=��Q�l�����PGo6�%�C�-�w/�q��Wk7������e�8W//48�]�]E]���m�'�����R�/�$�yR��ԗ���7Aq� ��E�����U�2Aw�
��}I����ϛ�������8_��va�곯���@�i\� ��I��H�/5������u�� �i�Ϻ�b6�c�-��.�CKWk6��H%��`�5�s���9:����F9T��(ְ)��ӥȂ�ޓ�j����)EU&��mF{G� lhZ�gs=I�X��.�zUҊ�̗M����,�����t<�  n�dI�p�X�&��9�Gv�����`R����/�X2'9��מ?RU}��6�I�*<��� 2�&�"�P�=L� /���KƩ��=�g%u���@Z�a`�0&����֪FG��Bwb��Vꌨ�n�Yb��U���V:�~��f��V<H
䰣D�B�a!t�2Y,Ǩ}�=\���#�����t:NC�5�[�)�d܁CH7�R'y��b+��J?9�ɡ�,C�2�T?��r���Pׇ5({S���0��S'OG�q"|�EU׼� >�n
�,�H���#���ۇZ�(B�[�>.٦2#l��2E�� 3�L6~��\��kx���r�:�V��!��,6�}C�[���Ƌ��Yb;�����a���9�A�H6���GA�"���/�x�q�T��rh����{eN	O�ˑG�6�=Z��4�R�U�L<��F����_p�]b��c� ����.���¥�K�Ĺ탤�2|��y�}0���X����o�>̅�9(��DcShAYi�=R%�@� T&:���VM���l�Ņ�� J��E#AEe�$���~��pSH����?����=>�o�8O�����܏�U�Sro�K3��P��{�+�� @'���LPN~љ�M/\!�$��+W�W{�;O�'�]����D�g�������Y"�o�~�r�u�s�V�T%�xq��Rh���}e���[����`50������,H����2c�1�_�3���J�^U*-��W�����B�7�O]���>�|�P��9�p�=��Y�P��%����bݡ�f$��6?�a�m�S�oֹQz��~�� `=Y����Wd[�'�h�
�N�G�,���i�C4 P��Ċ�����YV��s�]��>Qd��R���ǎ���(?rd����ӇG���G=�ْ�.L�ĸ�M��ePI�d~��^Zx@3�{rTP��Z��y��˥�h�H.��{t�W�ix�c�K<����g}
iC���֨zw-W,o�`��i��k����-q~�W������K^F��p�e"����i0�[�"1$���\0+G���O������gv�D~h�`P�;����&�|f�P
����#2��0�wc73���(��\;F�ylg���`y�<�y���/`��)V� -�����"|11EU�J��M֩��C�S��   �  Lga2/stage/AmpedScript; e start dash walk breke jump flow land dbljump attack damage die fall aim 	aimtarget 
chargestop 
chargewalk move (Lkotori/gfx/KGLScriptModel;)V (Lkotori/gfx/KGLScriptModel;Z)V stop1 S_A1 S_A1_2 S_b1 S_B1 S_B1_2 	invisible invisible_end finishpartsview removemodel tempInt (I)Ljava/lang/Integer;
 d � � setAbsoluteShadow (Z)V
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D %/accs/necromancer_gre/spector_efx.scm�            ?�  ?�  ?�              0@  setConnectVisible
 � � � clearGenerate
 d � 1 setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � � startScript (Ljava/lang/String;)Z
 � � � ()Lkotori/geom/Vector3D;
 H  � POS ()Lkotori/geom/Position;
 H � � (FFF)Lkotori/geom/Vector3D;
 H  ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone ()Ljava/lang/Object;
 � � � getPosition .(Lkotori/geom/Position;)Lkotori/geom/Position;
 f � � 	getMatrix ()Lkotori/geom/Matrix;
 � � �  e [F	 � � � getHPB .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � R2D (F)F
 � � � 	 � Q C�   D2R
 � � � Lkotori/geom/Position; � dir	 � � ô  C4  �4  C3  <� getHPBQuaternion 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � 2(Lkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � + � rot	 � � � sin
 H � � cos
 H � �
 � + �	 � S 	 � R  Lkotori/scp/KotoriScript; � realtime
 � � 2
 � ) �=���@H��:�o:ě�;o (FFFF)Lkotori/geom/Position;
 � + � ([FI)F
 H * �
 � + � sub
 � � � scale (F)Lkotori/geom/Vector3D;
 � � �
 � ) � pos (Lkotori/geom/Vector3D;)V
 � � � getStatusModifierFlags (ILjava/lang/String;)I
 f � � necromancer temp
 d � � Ljava/lang/Integer; � intValue
 � � 2 back isVisibleParent ()Z
 f � � 
setVisible
 f  �
 H 0 3 f  g 1  @*� �W*� �W*� �L+� +� �W*�� �L+� 	+� �W*� �W*+� �W*+� W�  h 1  *� �L+� 
+h� �W*+� W�  / 1  *� �L+� 
+/� �W*+� W�  i 1  *� W�  h 1  *� W�  j 1  *� W�  k 1  *� W�  l 1  *� W�  m 1  *� W�  n 1  *� W�  o 1  *� W�  p 1  *� W�  q 1  *� W�  r 1  *� W�  s 1  *� W�  t 1  *� W�  u 1  *� W�  v 1  *� W�  w x  *+� W�  w y E� �N� �:� �:� �:�:� �:� *� ���+��+� ���+� �� ���+� �� �W� �v� �8�� 
�b8� �8� Ƹ �8		��� 	Ǖ� 
	�r8		8
� �8
f8ȕ� �f8� ɕ� 
�b8ɕ� ʕ� 
�r8�� �jb8� �W+� �� �W� �	� �j� �	� �jb� �� �	� �j� �	� �jf� �W��*� ݆�jb� �j��*� ݆�jb� �j��*� ݆�jb� �j� �W� �� �b� �� �b� �� �b	� �W+� �� �:-*� �*� �*� � �W� �-� �˶ �-� �W+� �W*1�� �6*� �:� 6� �� ,� � �� *� �:+� 
+�� �W*� �W*� �6*����ҶW*�W���  z 1  *� �L+� 
+/� �W*+� W�  { 1  *� �L+� 
+{� �W*+� W�  | 1  *� �L+� 
+|� �W*+� W�  } 1  *� �L+� 
+}� �W*+� W�  ~ 1  *� �L+� 
+~� �W*+� W�   1  *� �L+� 
+� �W*+� W�  � 1  *� �L*+� W�  � 1  *� �L+� 
+�� �W*+� W�  � 1  *� �L+� +� �W�  � 1  *� W� \2�B            B�  ?�  B�  �  