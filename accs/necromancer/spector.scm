	� G   o�       �X{oG�ۑ�O?�TtN쳝@r��"^)A�������}���;���w.��"-*����BDʣ��"Bʇ�8q�Egw���� �e������;�w�x��Z�Be�_�l��š��KES1�lw�R5t�FS����X�zU��L�g��P�dOmd�$[j�b#9�ћ��b�ԐmKũ����yҸ����gĽ��r��@Y$Εƫ�NwWwWQ�`�f[��	�jħg��K�H}�Կ&�%���M�C���2p`�18f�L�]���q_������#���8�竵�.�[}�ms�h�=��`AܿH������Uw��C������1m!�EwW��UcL��t���ca{�`�j�����l��}���c<G��V}�(����6%{�Y��{rZMU㾴8����d����h����M+�l�'%�VJ�ES�JZ��A��"�(�X�ĳR �vN�Tg��elbؕ�>d�^�\�
&K)������%3p�c�x�i�Tէ[hs�ؗ$��C�&� �Lr-BU���� ���d���h�3VR��y����`�)ܑ�y�jtd�*�p'�*i�Έ
ꖚ%v6l�P��3[�̃�U;�U�[� )�Î�
凅�Qd�d���E��p��@~#mOCt:NC�5�[�)�d܁CH7�R'y��b+��J?9�ɡ�,C�2�t�Z���L������Qmg}禎�#�8�Ţ�k�A Y7�l�a����Q	��m���C-}!�g�lS�6KG�"�z��J�?�m.��5<x��J�v�^)��K
P׾#έ��|��U�,��������@��Š$U�� UO���/�x�q�t��rh�4��{��*���#�bm.�Y{��i��ܙxv��ƋͿ᲻Į�'p9�/N_]��݃Kk���s�I�d����T�`.	<��7G��}�1sP��ƦЂ��7z�J(�
A�Lte>��(	���1�A�l�F6���RI�������6y��o��{ |�q4�]g��OĹ�+�$-���0�f��
���W<!A�NN�ϙ����3u�^�B�I�VW���<� w��O��Ls[���ω��?�C��D�G�~�r�u�q�V�T%�xq��Rh���}e���[�����\����@�W�ag�RIH��1р��/��XRm�
B/�*�����dB�n{�~�7�O]���>�|	(C�8�f��,}(y�R���x���93^V��~�0��)�7��(�Npe�������caaE��+���i��Q�jA��l��egZ��!��db��N\��,+|z��ۮYp�(��͏T�l	C�c��N�92tb����#GN��$POd����0.c)vT�	���׀���Ĥ�� s^��0��ri*�*��e��� ƕv^��X��!���9�B�>��5��]����%�a������e�~O���c��E?�ҟ�Q�2F|��ĩ��r��V�Hɲ�l9�ʑ�5��k�(�#k���1��5T�`��wH��P
����#2��0�wc73���(��\;F�ylg���`y�<�}���5/`��)V� -���[���"|11EU�J��M֩��_f\��   �  Lga2/stage/AmpedScript; e start dash walk breke jump flow land dbljump attack damage die fall aim 	aimtarget 
chargestop 
chargewalk move (Lkotori/gfx/KGLScriptModel;)V (Lkotori/gfx/KGLScriptModel;Z)V stop1 S_A1 S_A1_2 S_b1 S_B1 S_B1_2 	invisible invisible_end finishpartsview removemodel tempInt (I)Ljava/lang/Integer;
 d � � setAbsoluteShadow (Z)V
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D !/accs/necromancer/spector_efx.scm�            ?�  ?�  ?�              0@  setConnectVisible
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
+�� �W*+� W�  � 1  *� �L+� +� �W�  � 1  *� W� \2Ρ            B�  ?�  B�  �  