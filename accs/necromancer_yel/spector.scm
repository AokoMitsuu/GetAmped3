	� G   t�       �X{OG��H��O�!jt|�!�CƑ���EC�TU�u���+��ݙ R�ܤi�(�6jҦii�B�R�6
��0����!:�{/�M@	²�ffg~�ڝ��𴍵��JR�h�R	����bؙ��b覍&u[7����ؽ�,OG��.ɞ��(J��B�Fr��;�Œ�Aۖ
��;���~O�{}u�6q��H���W���ΎΎ���VͶB��TհO�P)��L��<�}MjKT����8O�e���ap�*T���H��"΃���� �#q~%�Wk�\ط�����M�{��Â���]R���+�����)])"�c�B��Ύ��+ƨf�8������`��ժ���RQ?+�f�ܣ#�x���E���Q��=�5lJ6�t)���d����}iqRQU��D�{��ޑ�' �R��lWR*����^��6s3XMnTWYI�B:�A�����Z8�L,c��,�#3�l��0W0�XJt�T,����kO���>�D��Ŀ(Ye�7�g �f�k���.&���|��%�Tu[����:��s M�00-�yO��ϫ�-SV���1UI+�G�W��,��a�*p�g+�yP�j{�Je+$r�V�P���ڊl�,�cԺ��.�Qz��H��Ћ��!Ś�ȔN0n�!�P���tK�]C%����PV��U�|����S��t���*���Qmg}禎�#�8�ł�k�A Y7�L�a����Q	��m���CM}!�-g�lS�6KG�"�z��J&�?�i,��5<x��Z�z�^+�u��
P�W�#����|���,������ؐ@��Š$��� UO��/N{�q�T�z������{��ʞ��#�bm,�^{��i�&�ܙxv��������»Ȯ��pA�/N_]��݅Kk��s�I�d�йsT�@6<��7G��}�1sP���&߄��7z�J(��A�Lt�?��(	���1��h�F6���PI������,�6y��O��]>�o�8��O����'�܋�U�S�rw�K3�zP��{�+�� @'���LPN~љ�M/\!�$��+��W{�;O�'�]����B���������Y"�CR�A���9l�I��w�8�m)�����2�]�-F���q&X��D �+�3R�(�Ĵ̘�_L�{L,��R��B�J�`�u2!j��P�웆ڧ���to�^(Cq�?̰�Y�0K�޾d��?^�;t֌���槟!�my���:7J�܋�} �_�G��ぱ�����م�4��(Zռ��_6��2�� -rZ�Tx2�bf'�mi�>���mW-�OY��G�T�����c��?�<1r����#'F�{QWd���31.a)v	T�)���׀���,Ą�V!s^��0�g�)*�,�ͦ��� ƕ�^��X��!���Y�B�>��5*�]����$�a����ڣe�~O����c��E?�ҟ�Q�2F|�Hǩ��r��V�Hɲ�l9�ʑ�5��k�(�#k����1��5T�`��vH�o�P����#2��0�vc73���(��\;F�ylg���`9�<�}���5/`��)V� -��[���"|11EU�J��M֩����   �  Lga2/stage/AmpedScript; e start dash walk breke jump flow land dbljump attack damage die fall aim 	aimtarget 
chargestop 
chargewalk move (Lkotori/gfx/KGLScriptModel;)V (Lkotori/gfx/KGLScriptModel;Z)V stop1 S_A1 S_A1_2 S_b1 S_B1 S_B1_2 	invisible invisible_end finishpartsview removemodel tempInt (I)Ljava/lang/Integer;
 d � � setAbsoluteShadow (Z)V
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D %/accs/necromancer_yel/spector_efx.scm�            ?�  ?�  ?�              0@  setConnectVisible
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
+�� �W*+� W�  � 1  *� �L+� +� �W�  � 1  *� W� \2�N            B�  ?�  B�  �  