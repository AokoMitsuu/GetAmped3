	� G   u�       �X{oG�ۑ�O?�TtN��#Ї#%���WJP������g_s�;��!�D����G�
-�%�5�QUj!��l��R?Dgw���� �e������;����36�J*KѲ�2��.MMŰ��]J��MM�n*b�U�w3Y��RC]�=��Q�l�����PGo6�%�C�-�w/�q��Wk7������e�8W//48�]�]E]���m�'�����R�/�$�yR��ԗ���7Aq� ��E�����U�2Aw�
��}I����ϛ�������8_��va�곯���@�i\� ��I��H�/5������u�� �i�Ϻ�b6�c�-��.�CKWk6��H%��`�5�s���9:����F9T��(ְ)��ӥȂ�ޓ�j����)EU&��mF{G� lhZ�gs=I�X��.�zUҊ��53�!P=Yd%�xA@��ɒj�,2��M;sԏ��+���#\��b)��_T�dNr��=~���tm.��dUx�_'�d�M�E衪{�| ^�uc��S��{��J�7ρ�0���aL<�;r?�U��LY�:��T%��QA�R��·-��	Й�t�A����*խx��aG�b���B�(�e�X�Q�"b{��G�G~#mOC?*�:t��k��"S:ɸ��n@�N�>�-�Vt�8~r��CYY�Ve�~�k�N�ӡ�kP��F��a���N��l�D����y|d��Y�����G%F��ڷ��Q� ��}\�MeF�,e���Af*�l�x��<O�����k��uz��7C,)@Yl\��8�+�W���v���#��s.���lT���TE<�9$$(_��$��
D�8H����;)N�ʜ*���#�bm.�Y{��i��ܙxv����Ϳ�»Į��pA��N_]��݅Kk���s�I�d����T�`.<��7G��}�1sP��ƦЂ��7z�J(�
A�Lt����(	���1�A�h�F6���RI�������6y��O�{ |�q4�^g��Ĺ�+�$%���0�f��
���W<!A�NN�ϙ����3u�^�B�I&WW���<� w��O��Ls[���ψ�7�?����D��H�&���0�=�&�J���l����+��<�LoQ���;�s�j6`&q_���Y�J%!%�e�Dbz��gbI��*��TZ��9Q��2�oj��?3}�����s��{�������K&���źCg�HxYm~�	۶�߬s��:��(���z �����-&��.��	NFѪ�����Y�h��h@��3;q5hK�����n�f�}��
6?P��%�;�Q~��Љ�ç�916ҏz"�%�]�>��q�H�ˠ�N��������fF�䨠 &%����)?�KQ�V�\.���1�����
0�xy=���҆��w�Q��Z0�X�<.� �=t�-�[�����-��!������0��D:N���`�:EbH�%`˹`V���ןX�G)Y;�����Ю���w #��M �̶�v1"��Gdxa��n&fx��Q4��v�f���ޣ7����yz�.�'�+^&"�dES��Z,��7-��E��bb��J�rC��S���l���   �  Lga2/stage/AmpedScript; e start dash walk breke jump flow land dbljump attack damage die fall aim 	aimtarget 
chargestop 
chargewalk move (Lkotori/gfx/KGLScriptModel;)V (Lkotori/gfx/KGLScriptModel;Z)V stop1 S_A1 S_A1_2 S_b1 S_B1 S_B1_2 	invisible invisible_end finishpartsview removemodel tempInt (I)Ljava/lang/Integer;
 d � � setAbsoluteShadow (Z)V
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D %/accs/necromancer_pur/spector_efx.scm�            ?�  ?�  ?�              0@  setConnectVisible
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
+�� �W*+� W�  � 1  *� �L+� +� �W�  � 1  *� W� \2�[            B�  ?�  B�  �  