	� G %/accs/floating_arms/floating_arms.mot ��       �Wmo�V�\$��]���:��6�P(R�eM��i��&��_2��$�Hu;F�Z�L0����/�~ˬ�����ν�v��e������9�s��ܦخx�����5�L[M\).i���m�j:.E�:.Q*:Օ�u�v����v�:0�!Q�~`86l*|���aoD�sHͥR���m#�'�G�&�����tA�u��%K9N4�q�bK�)Q��e�~�iS�<)�i�s���Yb�s-[�r2|��&^}�4�o���?4L����]�P�dL@��iǁ�G!44G𙩔���VMG�Į�t��T��8t�䵳%�ALS�+U��(�qɥ�qD9��+���`�y���6�����{{>�W�R��W��v�4�u������DS�n�f!I��+.[k���G�3�q��!2ł�)�!j)���|��@G���K��4�
.��	-�S )3}\�;H��ǀ	q�N3T�޻C��/FD���~ڭ�I1��"����M�Ԗ"�����:lo�CR0i��V�w/���_����.�����\�s|5x.���I����S�=ڹ�)�s�"�F��"� n��U��W�5�n���+�'X�,��?3N#2�z�<�h]�����A�J�*,s`�C`F8t,�3�f%M�N�Z:<r1m�6�rNfN���_��^=Pe�£��w@�]����{����=xU!n�};�Ӻ�¥�H�0���!�}ĕ�i!mu@~S�P*�H��=�O�td93��'���+�Bw�x`�:�l�B�F:���!:0�)���$��b&@�t�T��l'�_'�?�h	<8��R<@��E��ge�� �]B��j��|���y>X�c�������_�c�`M��ӵ����z�X�ʭ��̊���K���8���MT]�f��YPJ�
���9�%:L�a�+q�-bKy���D>��(r�z,�Wm�Z:g���,@ث.�}zw�ɻ�䈸{+��^0�/Zp�]��Tס ����
��a:6�/L�ʅ���ޅW��>��@����<[��{� �Z�p�_e���DTD���03'O����j0�!{Vu\�LiDvg���X�t����X6=��O 5��U���P"������B�Z�s�Q�y��� dU=����ي�<9��\���١���y~�Q����oY)/�a�QO�]�X����ʳ��Q:�}ӆ��h�1�D�ó��)�qiƥ�%(��2֭T<���Bh����?���!�D�y��;E޹r���-��k�R�h �X�L�$M��'vqur./JV2�6���j[��lX�i��գ-8Ԅ����h�x��<C����Ҋn�BJ'�k�P�h�k
�Je���`$fj��)�z6쩢X(e�ezvv�T<xl�m^����:6	n>n���O\%�_�j�c{˄I��1�Ўa����l��a!*�pw�fk�.�5���U�x����>���?U	9�}���"���{6ؿ����   }  Lga2/stage/AmpedScript; e start floating_arms_explode_x_execute 
shotCalc_x (Lkotori/geom/Vector3D;)V shot_x (Lkotori/geom/Quaternion;)V broken 
setVisible (Z)V
 f n o setShadowState
 d q 3 lockItemScript (ZZ)V
 f s t
 H 0 3 lockTimeCount
 f w o finishAllConnections
 d y 1 clearGenerate
 d { 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d } ~ setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D (/accs/floating_arms/shootR_sx1_skill.scm�            ?�  ?�  ?�               start2 isServer ()Z
 f � � getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � length ()F
 � � �@�  �� kotori.geom.Quaternion?}�5>�e         Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � �
 f � �C4  C�  �4  	 � R  (FF)F
 � � � min
 � � �B4  A�  ��?�               Lkotori/geom/Quaternion; � clone
 � � �=��� getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � Lkotori/gfx/KGLScriptModel; � rotate (Lkotori/geom/Quaternion;I)V
 � � ���?}�5>�e         startScript (Ljava/lang/String;)Z
 � � � beam vibe (FFFFI)V
 d � �<#�
 	playSound
 d � 3�X��D /efx/gun/shell_floating_x.scm�        ����@@  @@  @@              � hit_be  d� �    a��       ,�>�         	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-             beam_end efx_end f  g 1  }*� pW*� rW*� uW*d� vW*� uW*� xW*� zW*� |W**��� � �W* ȶ vW*� ��  L*� �M,� 
*,� �L*h+� �W*� vW*� �W*� �W�  h 1  *� �L*� vW*+� W*d� vW*� W�  i j �*� �M,��+��*� �N+-� �W+� �8��� *,� vW*�� W�+� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8��� �f8� ��� 
�b8+� �8+� �+� �8		� �� �� �Zd�8

�� #b
j8b
j�
� ��nj8b8

�f8�:� �:6� /�`�jj8� �W,� �W*
� vW�W
���6� 0�`�jj8� �W,� �W*
� vW�W
���*d� vW� �W,� �W
� �W*� W� *,� vW*�� W�  k l  p*� �M,� 
,ж �W*^� vW*�\�� �W* ϶ �W�N6� '*+� �W*�-� �W*� �W* ȶ vW�W���,� 
,߶ �W�  m 1  @*� uW*� xW*� vW*� �L+� 
+� �W*�� vW*�� vW*� �W*� �W� FPk�    ?S(6���p?�L^?>t�?�[�  