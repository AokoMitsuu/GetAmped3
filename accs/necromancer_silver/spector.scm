	� G   x�       �X�oG��H�ҿ`	*:'���C���@C�+%(RUU��޳�9�w�R"�΅�RģE��ҒP���*�����l����/tv�^N��X�����o^�sy�?<cc�d���-[*cq�j��D�T;�ݥTݴєn�"��^{7��(5�%�S%ɖڨ�Hu�fc�X29d�Rq�q�2q7��v�)qo���!.P�s���B�����U�5ت�Vhp����Y*E��IR�'�/I}���|��	�\Xy�Y�*t���@ܗ�y�>����~O������m��>���x4����� ~����R㪻~�ozZWJ������+f�1��B*�b�=T�t�f㉊T��
�Y�>���1���z	�>`�Cel�b���=]�,x�=9���q_Z�RTU`2���f�w��	���|6ד��E+�ᢩW%��ͼ����Ln�UOYEI�B:�E�	�a�dI�p�X�&��9�Jv�� #\��b)�D��b���.D�4�����-��H
J�U�x���iB�����a�m x�׍]2NUw4�?+�S�<��ӆY0�����V5:2eJ�S��RgDuK�;"�p�
�@g�ҙ��v6�T��AR �%����Ȗ�b9F틈������=�����qR�iފL�$�B��:��p\�[�5dP���	NeeZ�ɧ�ᯕ;=�O��>�Aٛ�v����:y:���[,����uS�fF�.�`q�6h�>��G\t�q�6�a�t�)b���d������<]Ã'Oo�k�����0�� e�q���j��7^\%���N?���4,̹X�G�Q�>
R�T搐�|qƓ �+h�� ՗C�8=�s(sJ�x�X�<���pg��BĦa�Z�rg�Y�6�4��;����I>w8}u�Wv.�]Z&�m$�w��C��S탹<�X�b��~;�a.��AI\&�B�JK��*�*�2�A�~/�j�D$ds..ĤQ�E,�**;H%�OL��r@����?�?�A�X�!|�y�xz��?�~�������{�\�qԇ*\g�3^�:9�>g�r���mz�
'�\]����؃H�y�>!�2�m�'�>#�ߤ���6�q#���[�è�����*yǋ���B����(3�E�b�~�X��ـ��}vfA*��������r��%�V�p �B�Ri�J�@ D���Ⱦi�}�������2��s�a�?�҇��/�����7#�e���'Cn۞2|�΍����t ���z<0V���"�ؚ&8E�Vt��F8Zfq`�EN��
O&V���ՠ-Ͳ§����"+��@�ʖ04<vl��G��#C'F�><r���H?ꉌ�|va�`,�el"�.�J:(�����ݓ��"����d��>��\.EE[Er��ߣ;ĸ�N�kt+�\�9���?�SH��ߵFջk��by#�4L��]{�L�o���}����\��2
\�È/�8�?TN����!Y�p�-�Y92��_bm�pd�<�{8&�C�����|f7��3ۆR�ň���]�1������'FѼ��1��c;{����c���ͻ�`�x�� �M�*h���� ������)�*U�m�N��!�`�   �  Lga2/stage/AmpedScript; e start dash walk breke jump flow land dbljump attack damage die fall aim 	aimtarget 
chargestop 
chargewalk move (Lkotori/gfx/KGLScriptModel;)V (Lkotori/gfx/KGLScriptModel;Z)V stop1 S_A1 S_A1_2 S_b1 S_B1 S_B1_2 	invisible invisible_end finishpartsview removemodel tempInt (I)Ljava/lang/Integer;
 d � � setAbsoluteShadow (Z)V
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D (/accs/necromancer_silver/spector_efx.scm�            ?�  ?�  ?�              0@  setConnectVisible
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
+�� �W*+� W�  � 1  *� �L+� +� �W�  � 1  *� W� \2ќ            B�  ?�  B�  �  